(ns guideui.ui
  (:require [om.next :as om :refer-macros [defui]]
            [styles.util :as util :refer [to-cljs] :refer-macros [source->react defexample]]
            [untangled.client.core :as uc]
            [untangled.icons :as icons]
            [om.dom :as dom]
            styles.components
            styles.objects
            styles.elements
            styles.utilities
            [untangled.client.mutations :as m]))

(def parts {"Elements"   styles.elements/sections
            "Components" styles.components/sections
            "Objects"    styles.objects/sections
            "Utilities"  styles.utilities/sections})

(defn toolbar [component field options]
  (let [part-names options
        selected-idx (get (om/props component) field)
        get-class (fn [idx] (str "c-menu__link" (if (= idx selected-idx) " is-active" "")))
        select-item (fn [idx] (m/set-integer! component field :value idx))]
    (dom/div #js {}
      (dom/div #js {:className "o-toolbar o-toolbar--small"}
        (dom/ul #js {:className "c-menu c-menu--block"}
          (map-indexed (fn [idx nm]
                         (dom/li #js {:key idx}
                           (dom/button #js {:className (get-class idx)
                                            :onClick   #(select-item idx)} nm))) part-names))))))

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
      (dom/div nil
        (toolbar this :section/selected-example example-names)
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
      (dom/div nil
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
      (dom/div nil
        (toolbar this :parts/selected-part part-names)
        (ui-part (nth parts selected-part))))))

(def ui-parts (om/factory Parts))

(defui ^:once Root
  static uc/InitialAppState
  (initial-state [clz p] {:ui/react-key "A" :main-ui (uc/initial-state Parts nil)})
  static om/IQuery
  (query [this] [:ui/react-key {:main-ui (om/get-query Parts)}])
  Object
  (render [this]
    (let [{:keys [ui/react-key main-ui]} (om/props this)]
      (dom/div #js {:key react-key}
        (ui-parts main-ui)))))

