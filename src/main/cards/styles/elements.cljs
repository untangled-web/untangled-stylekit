(ns styles.elements
  (:require-macros user)
  (:require [om.next :as om :refer-macros [defui]]
    ;; NOTE: This is where the interesting macros and such are at:
            [styles.util :as util
             :refer [to-cljs]
             :refer-macros [source->react defexample]]
            [om.dom :as dom]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; START OF EXAMPLES
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Sample Example
(defexample form
  "## a simple form"
  (dom/div #js {} (dom/label #js {:htmlFor "input-1"} "Label")
           (dom/input #js {:id "input-1" :type "text" :placeholder "Input"})
           (dom/label #js {:htmlFor "select-1"} "Select")
           (dom/select #js {:id "select-1"}
                       (dom/option #js {} "Option 1")
                       (dom/option #js {} "Option 2")
                       (dom/option #js {} "Option 3"))))

(def form-header (devcards.core/markdown->react "Basic styles for form elements like `input`, `select` and `label`"))

(defexample image
  "## a simple image"
  (dom/div #js {}
           (dom/img #js {:src "/img/bubbles.png" :alt "generic image" :height "50" :width "50"})))

(def image-header (devcards.core/markdown->react "This section covers how we handle images. By default all `<img>` tags are 100% wide."))


(defexample layout
  "## a simple layout example"
  (dom/div #js {}
           (dom/div #js {:className "s-app__view"}
                  #_ (dom/header #js {:className "o-header"} " Header ")
                    (dom/main #js {:className "o-main"}
                              (dom/nav #js {:className "o-nav"} " Nav ")
                              (dom/article #js {:className "o-article"} " Article "))
                    (dom/footer #js {:className "o-footer"} " Footer "))))

(def layout-header (devcards.core/markdown->react "The main layout depends on the `.s-app` class to render a holy grail layout that scales down nicely to mobile.\n\n
 [Use the master layout CodePen](http://codepen.io/stephenway/pen/EKORdr)\n\n
 <svg width=\"725\" height=\"465\" viewBox=\"0 0 725 465\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:sketch=\"http://www.bohemiancoding.com/sketch/ns\"><title>Artboard 1</title><desc>Created with Sketch.</desc><g sketch:type=\"MSArtboardGroup\" fill=\"none\"><g id=\"Group\" sketch:type=\"MSLayerGroup\"><path fill=\"#047\" sketch:type=\"MSShapeGroup\" d=\"M26 31h356v44h-356z\"/><path fill=\"#cef\" sketch:type=\"MSShapeGroup\" d=\"M26 80h77v310h-77z\"/><path fill=\"#FCFCF9\" sketch:type=\"MSShapeGroup\" d=\"M109 80h273v310h-273z\"/><path fill=\"#EEEEE7\" sketch:type=\"MSShapeGroup\" d=\"M26 395h356v44h-356z\"/></g><g sketch:type=\"MSLayerGroup\"><path fill=\"#047\" sketch:type=\"MSShapeGroup\" d=\"M518 31h181.672v44h-181.672z\"/><path fill=\"#FCFCF9\" sketch:type=\"MSShapeGroup\" d=\"M518 80h181.672v262h-181.672z\"/><path fill=\"#EEEEE7\" sketch:type=\"MSShapeGroup\" d=\"M518 395h181.672v44h-181.672z\"/><path fill=\"#CAEEFF\" sketch:type=\"MSShapeGroup\" d=\"M518 346h181.672v44h-181.672z\"/></g><path d=\"M435 248v-30l30 15-30 15z\" fill=\"#EEEEE7\" sketch:type=\"MSShapeGroup\"/></g></svg>\n\nMore on this is available at Mozilla Developer Network's [Holy Grail Layout](https://developer.mozilla.org/en-US/docs/Web/CSS/CSS_Flexible_Box_Layout/Using_CSS_flexible_boxes#Holy_Grail_Layout_example)"))

(defexample typography-example-1
  "## Headings"
  (dom/div #js {}
           (dom/h1 #js {} "The quick, brown fox (20px)")))

(def typography-header-1 (devcards.core/markdown->react "All headings are the same size from the start, so we can concentrate on semantics out of the gate without having to worry about what size the heading will be. This is better than overwriting it with more styles, or choosing the wrong semantic heading to make it look right based on what size it gives you."))

(defexample typography-example-2
  "## Feature & Body Copy"
  (dom/div #js {}
           (dom/div #js {:className "is-featured"}
                    (dom/p #js {} "For the execution of the voyage to the Indies, I did not make use of intelligence, mathematics or maps. (24px)") " " (dom/p #js {} "After having dispatched a meal, I went ashore, and found no habitation save a single house, and that without an occupant; we had no doubt that the people had fled in terror at our approach, as the house was completely furnished. (16px)") )))

(defexample typography-example-3
  "## Lists"
  (dom/div #js {}
           (dom/ol #js {}
                   (dom/li #js {} "This is how an ordered list looks.")
                   (dom/li #js {} "I am struggling to say anything more about them.")
                   (dom/li #js {} "That brings us to the next type of list…"))
           (dom/ul #js {}
                   (dom/li #js {} "This is how an unordered list looks.")
                   (dom/li #js {} "I am struggling to say anything more about them.")
                   (dom/li #js {} "That brings us to tiny copy…") )))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; START OF SECTIONS (within a feature set...e.g. components)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; NOTE: This is where you add the sections for index
(def sections [
               ; NOTE: :examples is a list of example names, rendered in order given
               {:id :forms :title "Forms" :examples [form] :header form-header}
               {:id :images :title "Images" :examples [image] :header image-header}
               {:id :layouts :title "Layouts" :examples [layout] :header layout-header}
               {:id :typographies :title "Typography" :examples [typography-example-1 typography-example-2 typography-example-3] :header typography-header-1}
               ])

;; NOTE: How to render a section (with all examples) including hyperlink target anchor
(defn section
  [id sections]
  (let [{:keys [title examples header]} (first (filter #(= id (:id %)) sections))]
    (dom/div nil
             (dom/a #js {:id id}
                    (dom/h1 nil title))
             (dom/p nil header)
             (map #(%) examples))))

;; NOTE: Rendering of the clickable index
(defn section-index [sections]
  (dom/ul nil
          (map #(dom/li nil (dom/a #js {:href (str "#" (:id %))} (:title %))) sections)))

;; NOTE: Rendering of the main guide UI
(defui UI
  Object
  (render [this]
    (dom/div nil
             (section-index sections)
             (section :forms sections)
             (section :images sections)
             (section :layouts sections)
             (section :typographies sections)
             )))


