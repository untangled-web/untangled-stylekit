(ns guideui.ui
  (:require [om.next :as om :refer-macros [defui]]
            [styles.util :as util :refer [to-cljs] :refer-macros [source->react defexample]]
            [untangled.client.core :as uc]
            [untangled.client.mutations :as m]
            [untangled.icons :as icons]
            [om.dom :as dom]
            goog.string
            styles.components
            styles.objects
            styles.elements
            styles.utilities
            [untangled.client.mutations :as m]
            [clojure.string :as str]))

(def parts {"Elements"   styles.elements/sections
            "Components" styles.components/sections
            "Objects"    styles.objects/sections
            "Utilities"  styles.utilities/sections})

(defn make-section-index [part part-idx sections]
  (map-indexed
    (fn [section-index s]
      (map-indexed
        (fn [example-index e]
          {:path          [part-idx part section-index (:title s) example-index]
           :label         (str part ":" (:title s) ":" (:name e))
           :documentation (:documentation e)
           :terms         (:search-terms e)})
        (:examples s))
      ) sections))

(def index (flatten (map-indexed (fn [idx [part sections]]
                                   (make-section-index part idx sections)) parts)))

(defn find-results [term]
  (let [lcterm (str/trim (str/lower-case term))]
    (vec (filter (fn [{:keys [terms]}] (.includes terms lcterm)) index))))

(defn search [term]
  (cond
    (goog.string/isEmptyOrWhitespace term) []
    :else (find-results term)))

(defmethod m/mutate 'search/update-results [{:keys [state]} k {:keys [term]}]
  {:action (fn []
             (swap! state
                    (fn [m]
                      (-> m
                          (assoc-in [:ui :search :ui/search] term)
                          (assoc-in [:ui :search :results] (search term))))))})

(defmethod m/mutate 'guide/navigate [{:keys [state]} _ {:keys [path]}]
  {:action (fn []
             (let [[part-index part-title section-index section-title example] path]
               (swap! state
                      (fn [m]
                        (-> m
                            (assoc-in [:ui :search :ui/open] false)
                            (assoc-in [:parts :ui :parts/selected-part] part-index)
                            (assoc-in [:parts/by-title part-title :part/selected-section] section-index)
                            (assoc-in [:section/by-title section-title :section/selected-example] example))))))})

(defn navlist [component field options]
  (let [part-names options
        selected-idx (get (om/props component) field)
        get-class (fn [idx] (str "link" (if (= idx selected-idx) " is-active" "")))
        select-item (fn [idx] (m/set-integer! component field :value idx))]
    (dom/ul nil
            (map-indexed (fn [idx nm]
                           (dom/li #js {:key idx}
                                   (dom/button #js {:className (get-class idx)
                                                    :onClick   #(select-item idx)} nm))) part-names))))

(defn toolbar [component field options]
  (let [part-names options
        selected-idx (get (om/props component) field)
        get-class (fn [idx] (str "c-menu__link" (if (= idx selected-idx) " is-active" "")))
        select-item (fn [idx] (m/set-integer! component field :value idx))]
    (dom/div #js {}
      (dom/div #js {:className "o-toolbar"}
        (dom/ul #js {:className "c-menu c-menu--inline"}
          (map-indexed (fn [idx nm]
                         (dom/li #js {:key idx}
                           (dom/button #js {:className (get-class idx)
                                            :onClick   #(select-item idx)} nm))) part-names))))))


(defui ^:once SearchBar
  static uc/InitialAppState
  (initial-state [c p] {:results [] :ui/search "" :ui/open false})
  static om/IQuery
  (query [this] [:ui/open :ui/search :results])
  static om/Ident
  (ident [this props] [:ui :search])
  Object
  (render [this]
    (let [{:keys [ui/open results ui/search]} (om/props this)
          open (and open (pos? (count results)))
          menu-class (str "c-dropdown__menu" (when open " is-active"))]
      (dom/div #js {:className "c-dropdown"}
               (dom/div #js {:className "c-field c-field--medium"}
                     (dom/input #js {:type        "text"
                                    :value       search
                                    :onChange    (fn [evt]
                                                   (let [v (.. evt -target -value)]
                                                     (om/transact! this `[(search/update-results ~{:term v})])))
                                    :onFocus     #(m/set-value! this :ui/open true)
                                    :placeholder "Search"
                                     :className "c-field__input"})
                        #_(dom/span #js {:className "c-icon"} (untangled.icons/material-icon :search))
                        )
        (dom/ul #js {:tabIndex "-1" :aria-hidden "true" :className menu-class}
          (map (fn [{:keys [label path]}]
                 (dom/li #js {:key label :onClick (fn [evt]
                                                    (m/set-value! this :ui/open false)
                                                    (om/transact! this `[(guide/navigate {:path ~path}) :ui/react-key]))}
                   (dom/button #js {:className "c-dropdown__link"} label))) results))))))

(def ui-search (om/factory SearchBar))

(defui ^:once Example
  static uc/InitialAppState
  (initial-state [c {:keys [id title part section]}] {:example/title title :example/id id
                                                      :example/path  [part section :examples id]})
  static om/IQuery
  (query [this] [:example/id :example/title :example/path])
  static om/Ident
  (ident [this {:keys [example/path]}] [:example/by-path path])
  Object
  (render [this]
    (let [{:keys [example/path]} (om/props this)
          example-renderer (get-in parts (conj path :renderer))]
      (dom/div nil
        (example-renderer)))))

(def ui-example (om/factory Example {:keyfn :example/id}))

(defui ^:once Section
  static uc/InitialAppState
  (initial-state [c {:keys [part n]}]
    (let [section (get-in parts [part n])
          raw-examples (:examples section)
          examples (vec (map-indexed (fn [idx v]
                                       (let [name (-> v :name)]
                                         (uc/initial-state Example {:id idx :title name :part part :section n})))
                                     raw-examples))]
      {:section/title            (:title section)
       :section/selected-example 0
       :section/examples         examples}))
  static om/IQuery
  (query [this] [:section/title :section/selected-example
                 {:section/examples (om/get-query Example)}])
  static om/Ident
  (ident [this props] [:section/by-title (:section/title props)])
  Object
  (render [this]
    (let [{:keys [section/selected-example section/examples section/title] :or {section/selected-example 0}} (om/props this)
          example-names (map :example/title examples)]
      (dom/div #js {:className "ui-section"}
               (when (> (count examples) 1) (toolbar this :section/selected-example example-names))
        (ui-example (nth examples selected-example))))))

(def ui-section (om/factory Section {:keyfn :section/title}))


(defui ^:once Part
  static uc/InitialAppState
  (initial-state [c {:keys [title]}] {:part/title            title
                                      :part/selected-section 0
                                      :part/sections         (vec (map-indexed (fn [idx s] (uc/initial-state Section {:part title :n idx}))
                                                                               (get parts title)))})
  static om/IQuery
  (query [this] [:part/title :part/selected-section
                 {:part/sections (om/get-query Section)}])
  static om/Ident
  (ident [this props] [:parts/by-title (:part/title props)])
  Object
  (render [this]
    (let [{:keys [part/selected-section part/sections part/title] :or {part/selected-section 0}} (om/props this)
          section-names (map :section/title sections)]
      (dom/div #js {:className "ui-part"}
        (toolbar this :part/selected-section section-names)
        (ui-section (nth sections selected-section))))))

(def ui-part (om/factory Part {:keyfn :part/title}))


(defui ^:once Parts
  static uc/InitialAppState
  (initial-state [c p] {:parts/selected-part 0
                        :parts               (mapv (fn [t] (uc/initial-state Part {:title t})) (keys parts))})
  static om/IQuery
  (query [this] [:parts/selected-part {:parts (om/get-query Part)}])
  static om/Ident
  (ident [this props] [:parts :ui])
  Object
  (render [this]
    (let [{:keys [parts/selected-part parts] :or {parts/selected-part 0}} (om/props this)
          part-names (map :part/title parts)]
      (dom/div #js {:className "ui-parts"}
        (navlist this :parts/selected-part part-names)
        (ui-part (nth parts selected-part))))))

(def ui-parts (om/factory Parts))

(defui ^:once Root
  static uc/InitialAppState
  (initial-state [clz p] {:ui/react-key "A"
                          :searchbar    (uc/initial-state SearchBar nil)
                          :main-ui      (uc/initial-state Parts nil)})
  static om/IQuery
  (query [this] [:ui/react-key {:searchbar (om/get-query SearchBar)} {:main-ui (om/get-query Parts)}])
  Object
  (render [this]
    (let [{:keys [ui/react-key main-ui searchbar]} (om/props this)]
      (dom/div #js {:key react-key}
               (dom/div #js {:className "o-toolbar"}
                        (dom/div #js {:className "u-column--3"}
                                 (dom/h1 nil "Untangled Stylekit"))
                        (dom/div #js {:className "u-column"}
                                 (ui-search searchbar)))
        (ui-parts main-ui)))))

