(ns styles.cards
  (:require-macros user)
  (:require [om.next :as om :refer-macros [defui]]
            [untangled.client.core :as uc]
    ;; NOTE: This is where the interesting macros and such are at:
            [styles.util :as util
             :refer [to-cljs]
             :refer-macros [source->react defexample]]
            [om.dom :as dom]
            [devcards.util.edn-renderer :as edn]
            [untangled.client.mutations :as m]))

;; NOTE: to embed an HTML CONVERTER on div with id "example-1":
(def Client (uc/new-untangled-client))
(uc/mount Client util/HTMLConverterApp "example-1")

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; START OF EXAMPLES
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Sample Example
(defexample badge-example-1
  "## A plain badge"
  (dom/p #js {}
    (dom/a #js {:href "#"} "Inbox "
      (dom/span #js {:className "c-badge"} 7))))

(defexample badge-on-button
  "## A Badge on a Button"
  (dom/p #js {}
    (dom/button #js {:className "c-button"} " Messages "
      (dom/span #js {:className "c-badge"} "37"))))

(defexample badge-with-icon
  "## A Badge with an Icon"
  (dom/span #js {:className "c-badge c-badge--round"}
    (dom/span #js {:className "c-icon"}
      (dom/svg #js {:xmlns "http://www.w3.org/2000/svg" :width "24" :height "24" :viewBox "0 0 24 24"}
        (dom/path #js {:d "M15 1H9v2h6V1zm-4 13h2V8h-2v6zm8.03-6.61l1.42-1.42c-.43-.51-.9-.99-1.41-1.41l-1.42
                           1.42C16.07 4.74 14.12 4 12 4c-4.97 0-9 4.03-9 9s4.02 9 9 9 9-4.03
                           9-9c0-2.12-.74-4.07-1.97-5.61zM12 20c-3.87
                           0-7-3.13-7-7s3.13-7 7-7 7 3.13 7 7-3.13 7-7 7z"})))))

(defexample button-state
  "## Button States"
  (dom/div #js {}
    (dom/button #js {:aria-disabled "true" :className "c-button is-disabled"} "Disabled")
    (dom/button #js {:title "Click me to see the active state." :className "c-button is-active"} "Active")))

(defexample button-size-and-shape
  "## Button size and shape"
  (dom/div #js {}
    (dom/button #js {:className "c-button"} "Regular")
    (dom/button #js {:className "c-button c-button--large"} "Large")
    (dom/button #js {:className "c-button c-button--xlarge"} "Extra Large")
    (dom/button #js {:className "c-button c-button--round"} "Round")
    (dom/button #js {:className "c-button c-button--wide"} "Wide")
    (dom/button #js {:className "c-button"}
      (dom/span #js {:className "c-icon"}
        (dom/svg #js {:xmlns "http://www.w3.org/2000/svg" :width "24" :height "24" :viewBox "0 0 24 24"}
          (dom/path #js {:d "M20 11H7.83l5.59-5.59L12 4l-8 8 8 8 1.41-1.41L7.83 13H20v-2z"})))
      (dom/span #js {:className "c-button__content"} "Left Icon"))
    (dom/button #js {:className "c-button"}
      (dom/span #js {:className "c-button__content"} "Right Icon")
      (dom/span #js {:className "c-icon"}
        (dom/svg #js {:xmlns "http://www.w3.org/2000/svg" :width "24" :height "24" :viewBox "0 0 24 24"}
          (dom/path #js {:d "M12 4l-1.41 1.41L16.17 11H4v2h12.17l-5.58 5.59L12 20l8-8z"}))))
    (dom/button #js {:title "Icon Button" :className "c-button c-button--icon"}
      (dom/span #js {:className "c-icon"}
        (dom/svg #js {:xmlns "http://www.w3.org/2000/svg" :width "24" :height "24" :viewBox "0 0 24 24"}
          (dom/path #js {:d "M12.87 15.07l-2.54-2.51.03-.03c1.74-1.94 2.98-4.17 3.71-6.53H17V4h-7V2H8v2H1v1.99h11.17C11.5 7.92 10.44 9.75 9 11.35 8.07 10.32 7.3 9.19 6.69 8h-2c.73 1.63 1.73 3.17 2.98 4.56l-5.09 5.02L4 19l5-5 3.11 3.11.76-2.04zM18.5 10h-2L12 22h2l1.12-3h4.75L21 22h2l-4.5-12zm-2.62 7l1.62-4.33L19.12 17h-3.24z"}))))))

(defexample button-color
  "## Button Color"
  (dom/div #js {}
    (dom/button #js {:className "c-button"} "Default")
    (dom/button #js {:className "c-button c-button--secondary"} "Secondary")
    (dom/button #js {:className "c-button c-button--alert"} "Alert")
    (dom/button #js {:className "c-button c-button--passive"} "Passive")
    (dom/button #js {:className "c-button c-button--text"} "Text")
    (dom/button #js {:className "c-button c-button--anchor"} "Anchor")))

(defexample button-postfix
  "## Button Postfix"
  (dom/div #js {:className "u-row u-row--collapse"}
    (dom/div #js {:className "u-column--9"}
      (dom/input #js {:type "text" :placeholder "Search for..." :className "c-input"}))
    (dom/div #js {:className "u-column--2"}
      (dom/button #js {:className "c-button c-button--postfix"} "Search"))))

(defexample card-example
  "## Cards"
  (dom/div #js {:className "c-card"}
    (dom/h1 #js {} "Title")
    (dom/p #js {} "Card paragraph text goes here.")))

(defexample rounded-card
  "## Rounded Card"
  (dom/div #js {:className "c-card c-card--round"}
    (dom/h1 #js {} "Title")
    (dom/p #js {} "This is just a monolithic class that rounds off any card you apply it to.")))

(defexample transparent-card
  "## Transparent Card"
  (dom/div #js {:className "c-card c-card--transparent"}
    (dom/h1 #js {} "Title")
    (dom/p #js {} "This gives you the basic box properties without any background color or text color.")))

(defexample ruled-card
  "## Ruled Card"
  (dom/div #js {:className "u-wrapper"}
    (dom/div #js {:className "c-card c-card--ruled"}
      (dom/h1 #js {} "Title")
      (dom/p #js {} "A simple card, horizontal ruled."))))

(defexample card-with-titlebar
  "## Title Bar Card"
  (dom/div #js {:className "c-card"}
    (dom/div #js {:className "c-card__title"}
      (dom/h1 #js {:className "c-card__heading"} "Title"))
    (dom/p #js {} "Add these title and heading modifiers to your card to get a titlebar.")))

(defexample active-card
  "## Active Card"
  (dom/div nil
    (dom/div #js {:className "c-card c-card--row is-active"}
      (dom/h1 #js {} "Title")
      (dom/p #js {} "I could have used lorem ipsum, but what's the fun in that?"))))

(defexample inactive-card
  "## Inactive Card"
  (dom/div nil
    (dom/div #js {:className "c-card c-card--row is-inactive"}
      (dom/h1 #js {} "Title")
      (dom/p #js {} "I could have used lorem ipsum, but what's the fun in that?"))))

(defexample drop-card
  "## Drop Zone Card"
  (dom/div #js {:className "c-card c-card--zone"} "This is a Card Zone component! Drop things on me!"))

(defexample checkboxes
  "# Checkboxes"
  (dom/div #js {}
    (dom/input #js {:id "checkbox-1" :type "checkbox" :className "c-checkbox"})
    (dom/label #js {:for "checkbox-1"} "Checkbox")
    (dom/input #js {:id "checkbox-2" :type "checkbox" :checked true :className "c-checkbox"})
    (dom/label #js {:for "checkbox-2"} "Checked Checkbox")
    (dom/input #js {:id "checkbox-3" :type "checkbox" :className "c-checkbox is-indeterminate"})
    (dom/label #js {:for "checkbox-3"} "Indeterminate Checkbox")

    (dom/input #js {:id "checkbox-5" :type "checkbox" :className "c-checkbox c-checkbox--informative"})
    (dom/label #js {:for "checkbox-5"} "Checkbox")
    (dom/input #js {:id "checkbox-6" :type "checkbox" :checked true :className "c-checkbox c-checkbox--informative"})
    (dom/label #js {:for "checkbox-6"} "Checked Checkbox")
    (dom/input #js {:id "checkbox-7" :type "checkbox" :className "c-checkbox c-checkbox--informative is-indeterminate"})
    (dom/label #js {:for "checkbox-7"} "Indeterminate Checkbox")))

(defexample dropdown
  "## Normal Dropdown

  This example uses component local state to toggle the is-active class to open/close the dropdown."
  (let [open (boolean (om/get-state this :open))
        menu-class (str "c-dropdown__menu" (if open " is-active" ""))
        selections ["Apples" "Oranges" "Banannas"]
        current (or (om/get-state this :selection) "Not Selected")]
    (dom/div #js {:className "c-dropdown"}
      (dom/button #js {:onClick   #(om/update-state! this update :open not)
                       :className "c-dropdown__select js-dropdown-toggle"} current)
      (dom/ul #js {:id        "test-dropdown" :tabindex "-1" :aria-hidden "true"
                   :className menu-class}
        (map (fn [s]
               (dom/li #js {:key s :onClick (fn [evt]
                                              (om/update-state! this assoc :open false)
                                              (om/update-state! this assoc :selection s))}
                 (dom/button #js {:className "c-dropdown__link"} s))) selections)))))

(defexample dropdown-large
  "## Large Dropdown"                                       ; NOTE: markdown format doc string. Do not include cljs in it.
  (let [open (boolean (om/get-state this :open))
        menu-class (str "c-dropdown__menu" (if open " is-active" ""))
        selections ["Apples" "Oranges" "Banannas"]
        current (or (om/get-state this :selection) "Not Selected")]
    (dom/div #js {:className "c-dropdown c-dropdown--large"}
      (dom/button #js {:onClick   #(om/update-state! this update :open not)
                       :className "c-dropdown__select js-dropdown-toggle"} current)
      (dom/ul #js {:id        "test-dropdown" :tabindex "-1" :aria-hidden "true"
                   :className menu-class}
        (map (fn [s]
               (dom/li #js {:key s :onClick (fn [evt]
                                              (om/update-state! this assoc :open false)
                                              (om/update-state! this assoc :selection s))}
                 (dom/button #js {:className "c-dropdown__link"} s))) selections)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; START OF SECTIONS (within a feature set...e.g. components)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; NOTE: This is where you add the sections for index
(def sections [
               ; NOTE: :examples is a list of example names, rendered in order given
               {:id :badges :title "Badges" :examples [badge-example-1 badge-on-button badge-with-icon]}
               {:id :buttons :title "Buttons" :examples [button-size-and-shape button-color button-state button-postfix]}
               {:id :card :title "Card" :examples [drop-card active-card inactive-card card-with-titlebar card-example rounded-card transparent-card ruled-card]}
               {:id :checkboxes :title "Checkboxes" :examples [checkboxes]}
               {:id :dropdowns :title "Dropdowns" :examples [dropdown-large dropdown]}
               ])

;; NOTE: How to render a section (with all examples) including hyperlink target anchor
(defn section
  [id sections]
  (let [{:keys [title examples]} (first (filter #(= id (:id %)) sections))]
    (dom/div nil
      (dom/a #js {:id id}
        (dom/h1 nil title))
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
      (section :dropdowns sections)
      (section :checkboxes sections)
      (section :card sections)
      (section :badges sections)
      (section :buttons sections))))

;; NOTE: Mount the main UI on div with ID example-2
(def Client2 (uc/new-untangled-client))
(uc/mount Client2 UI "example-2")
