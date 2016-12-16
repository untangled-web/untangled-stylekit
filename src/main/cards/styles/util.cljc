(ns styles.util
  #?(:clj
     (:require [devcards.core :as dc :include-macros true]
               cljs.repl
               [clojure.string :as str])
     :cljs
     (:require [devcards.core :as dc :include-macros true]
       [clojure.set :as set]
       [clojure.string :as str]
       [hickory.core :as hc]
       [untangled.client.mutations :as m]
       [untangled.client.core :as uc]
       [om.next :as om :refer [defui]]
       [om.dom :as dom]
       [devcards.util.markdown :as md]
       [devcards.util.edn-renderer :as edn])))

#?(:clj
   (defmacro source->react [obj body]
     (let [code (cljs.repl/source-fn &env obj)
           ; HACK: Use the first form in the sexp to find the end of the doc, so we can trim out the macro source
           marker (str "(" (first body))
           beginning (- (.indexOf code marker) 2)
           ; HACK: drop the last closing paren
           end (- (.length code) 1)
           code (.substring code beginning end)]
       `(devcards.core/markdown->react
          (dc/mkdn-code
            ~(or code (str "Source not found")))))))

#?(:clj
   (defmacro defexample
     "Define a DOM example. The body may reference `this` as if in the body of
     an Om component `(render [this] ...)`"
     [sym doc body]
     (let [basename (str sym "-")
           root (gensym basename)
           symfn (symbol (str (name sym) "-code"))]
       `(do
          (defn ~symfn [~'this] ~body)
          (om.next/defui ~root
            ~'Object
            (~'render [this#]
              (om.dom/div nil
                (om.dom/div (cljs.core/clj->js {:dangerouslySetInnerHTML (cljs.core/clj->js {:__html (devcards.util.markdown/markdown-to-html ~doc)})}))
                (om.dom/div (cljs.core/clj->js {:className "u-row"})
                  (om.dom/div (cljs.core/clj->js {:className "u-column--12"}) (styles.util/source->react ~symfn ~body)))
                (om.dom/div (cljs.core/clj->js {:className "u-row"})
                  (om.dom/div (cljs.core/clj->js {:className "u-column--12"}) (~symfn this#))))))
          (def ~sym {:name          ~(name sym)
                     :documentation ~doc
                     :search-terms  ~(str/join " " (map str/lower-case [doc (name sym)]))
                     :renderer      (om.next/factory ~root {:keyfn (fn [] ~(name root))})})))))

(def attr-renames {
                   :class        :className
                   :for          :htmlFor
                   :tabindex     :tabIndex
                   :viewbox      :viewBox
                   :spellcheck   :spellcheck
                   :autocorrect  :autoCorrect
                   :autocomplete :autoComplete
                   })
#?(:cljs
   (defn elem-to-cljs [elem]
     (cond
       (string? elem) elem
       (vector? elem) (let [tag (name (first elem))
                            attrs (set/rename-keys (second elem) attr-renames)
                            children (map elem-to-cljs (rest (rest elem)))]
                        (concat (list (symbol "dom" tag) (symbol "#js") attrs) children))
       :otherwise "UNKNOWN")))

#?(:cljs
   (defn to-cljs
     "Convert an HTML fragment (containing just one tag) into a corresponding Om Dom cljs"
     [html-fragment]
     (let [hiccup-list (map hc/as-hiccup (hc/parse-fragment html-fragment))]
       (first (map elem-to-cljs hiccup-list)))))


#?(:cljs
   (defmethod m/mutate 'convert [{:keys [state]} k p]
     {:action (fn []
                (let [html (get-in @state [:top :conv :html])
                      cljs (to-cljs html)]
                  (swap! state assoc-in [:top :conv :cljs] cljs)))}))

#?(:cljs
   (defui HTMLConverter
     static uc/InitialAppState
     (initial-state [clz params] {:html "<div> </div>" :cljs (list)})
     static om/IQuery
     (query [this] [:cljs :html])
     static om/Ident
     (ident [this p] [:top :conv])
     Object
     (render [this]
       (let [{:keys [html cljs]} (om/props this)]
         (dom/div #js {:className ""}
           (dom/textarea #js {:cols     80 :rows 10
                              :onChange (fn [evt] (m/set-string! this :html :event evt))
                              :value    html})
           (dom/div #js {} (edn/html-edn cljs))
           (dom/button #js {:className "c-button" :onClick (fn [evt]
                                                             (om/transact! this '[(convert)]))} "Convert"))))))

#?(:cljs
   (def ui-html-convert (om/factory HTMLConverter)))

#?(:cljs
   (defui HTMLConverterApp
     static uc/InitialAppState
     (initial-state [clz params] {:converter (uc/initial-state HTMLConverter {})})
     static om/IQuery
     (query [this] [{:converter (om/get-query HTMLConverter)} :react-key])
     static om/Ident
     (ident [this p] [:top :conv])
     Object
     (render [this]
       (let [{:keys [converter ui/react-key]} (om/props this)]
         (dom/div
           #js {:key react-key} (ui-html-convert converter))))))

#?(:cljs
   (defn section
     "Render a section with examples."
     [id sections]
     (let [{:keys [title documentation examples]} (first (filter #(= id (:id %)) sections))]
       (dom/div nil
         (dom/a #js {:id id}
           (dom/h1 nil title))
         (when documentation
           (dom/div nil (dc/markdown->react documentation)))
         (map-indexed (fn [idx render]
                        (dom/div #js {:key (str "section-" idx)}
                          (render))) examples)))))

#?(:cljs
   (defn section-index
     "Render a clickable index of a given set of sections."
     [sections]
     (dom/ul nil
       (map #(dom/li nil (dom/a #js {:href (str "#" (:id %))} (:title %))) sections))))

