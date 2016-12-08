(ns styles.util
  #?(:clj
     (:require [devcards.core :as dc :include-macros true]
               cljs.repl
               [clojure.string :as str])
     :cljs
     (:require [devcards.core :as dc :include-macros true]
       [clojure.set :as set]
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
           marker (str "(" (first body))
           beginning (- (.indexOf code marker) 2)
           end (- (.length code) 1)
           code (.substring code beginning end)]
       `(devcards.core/markdown->react
          (dc/mkdn-code
            ~(or code (str "Source not found")))))))

#?(:clj
   (defmacro defexample [sym doc body]
     (let [root (gensym "Example")
           symfn (symbol (str (name sym) "-code"))]
       `(do
          (defn ~symfn [] ~body)
          (om.next/defui ~root
            ~'Object
            (~'render [this#]
              (om.dom/div nil
                (om.dom/div (cljs.core/clj->js {:className "u-row"})
                  (om.dom/div (cljs.core/clj->js {:className ""}) (styles.util/source->react ~symfn ~body)))
                (om.dom/div (cljs.core/clj->js {:dangerouslySetInnerHTML (cljs.core/clj->js {:__html (devcards.util.markdown/markdown-to-html ~doc)})}))
                (om.dom/div (cljs.core/clj->js {:className "u-row"})
                  (om.dom/div (cljs.core/clj->js {:className ""}) (~symfn))))))
          (def ~sym (om.next/factory ~root {:keyfn (fn [] ~(name root))}))))))

#?(:cljs
   (defn elem-to-cljs [elem]
     (cond
       (string? elem) elem
       (vector? elem) (let [tag (name (first elem))
                            attrs (set/rename-keys (second elem) {:class :className :viewbox :viewBox})
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
           (dom/textarea #js {:cols  80 :rows 10 :onChange (fn [evt]
                                                             (m/set-string! this :html :event evt))
                              :value html})
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

