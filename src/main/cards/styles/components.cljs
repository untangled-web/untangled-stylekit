(ns styles.components
  (:require-macros user)
  (:require [om.next :as om :refer-macros [defui]]
            [styles.util :as util :refer [to-cljs] :refer-macros [source->react defexample]]
            [untangled.icons :as icons]
            [om.dom :as dom]))

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
  "# Checkboxes

  The following examples show the various rendered states of checkboxes.
  "
  (dom/div #js {}
    (dom/input #js {:id "checkbox-1" :type "checkbox" :className "c-checkbox"})
    (dom/label #js {:htmlFor "checkbox-1"} "Checkbox")
    (dom/input #js {:id "checkbox-2" :type "checkbox" :checked true :className "c-checkbox"})
    (dom/label #js {:htmlFor "checkbox-2"} "Checked Checkbox")
    (dom/input #js {:id "checkbox-3" :type "checkbox" :className "c-checkbox is-indeterminate"})
    (dom/label #js {:htmlFor "checkbox-3"} "Indeterminate Checkbox")
    (dom/input #js {:id "checkbox-5" :type "checkbox" :className "c-checkbox c-checkbox--informative"})
    (dom/label #js {:htmlFor "checkbox-5"} "Informative (unchecked) Checkbox")
    (dom/input #js {:id "checkbox-6" :type "checkbox" :checked true :className "c-checkbox c-checkbox--informative"})
    (dom/label #js {:htmlFor "checkbox-6"} "Checked Informative Checkbox")
    (dom/input #js {:id "checkbox-7" :type "checkbox" :className "c-checkbox c-checkbox--informative is-indeterminate"})
    (dom/label #js {:htmlFor "checkbox-7"} "Indeterminate Informative Checkbox")))

(defn toggle-open [this] (om/update-state! this update :open not))

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
      (dom/ul #js {:id        "test-dropdown" :tabIndex "-1" :aria-hidden "true"
                   :className menu-class}
        (map (fn [s]
               (dom/li #js {:key s :onClick (fn [evt]
                                              (om/update-state! this assoc :open false)
                                              (om/update-state! this assoc :selection s))}
                 (dom/button #js {:className "c-dropdown__link"} s))) selections)))))

(defexample dropdown-large
  "## Dropdown with Large Toggle"
  (let [open (boolean (om/get-state this :open))
        menu-class (str "c-dropdown__menu" (if open " is-active" ""))
        selections ["Apples" "Oranges" "Banannas"]
        current (or (om/get-state this :selection) "Not Selected")]
    (dom/div #js {:className "c-dropdown c-dropdown--large"}
      (dom/button #js {:onClick   #(om/update-state! this update :open not)
                       :className "c-dropdown__select js-dropdown-toggle"} current)
      (dom/ul #js {:id        "test-dropdown" :tabIndex "-1" :aria-hidden "true"
                   :className menu-class}
        (map (fn [s]
               (dom/li #js {:key s :onClick (fn [evt]
                                              (om/update-state! this assoc :open false)
                                              (om/update-state! this assoc :selection s))}
                 (dom/button #js {:className "c-dropdown__link"} s))) selections)))))

(defexample dropdown-positive
  "# Dropdown with Positive Color"
  (let [open (boolean (om/get-state this :open))
        menu-class (str "c-dropdown__menu" (if open " is-active" ""))]
    (dom/div #js {}
      (dom/div #js {:className "c-dropdown"}
        (dom/button #js {:onClick   #(toggle-open this)
                         :className "c-dropdown__select c-dropdown__select--positive js-dropdown-toggle"} "Positive Select")
        (dom/ul #js {:id "test-dropdown" :aria-hidden "true" :className menu-class :tabIndex "-1"}
          (dom/li #js {}
            (dom/button #js {:className "c-dropdown__link"} "Banannas")))))))

(defexample dropdown-alterable
  "# Dropdown with Alterable Color"
  (let [open (boolean (om/get-state this :open))
        menu-class (str "c-dropdown__menu" (if open " is-active" ""))]
    (dom/div #js {:className "c-dropdown"}
      (dom/button #js {:onClick   #(toggle-open this)
                       :className "c-dropdown__select c-dropdown__select--alterable js-dropdown-toggle"} "Alterable Select")
      (dom/ul #js {:id "test-dropdown" :aria-hidden "true" :className menu-class :tabIndex "-1"}
        (dom/li #js {}
          (dom/button #js {:className "c-dropdown__link"} "Apples"))))))

(defexample dropdown-negative
  "# Dropdown with Negative Color"
  (let [open (boolean (om/get-state this :open))
        menu-class (str "c-dropdown__menu" (if open " is-active" ""))]
    (dom/div #js {:className "c-dropdown"}
      (dom/button #js {:onClick   #(toggle-open this)
                       :className "c-dropdown__select c-dropdown__select--negative js-dropdown-toggle"} "Negative Select")
      (dom/ul #js {:id "test-dropdown" :aria-hidden "true" :className menu-class :tabIndex "-1"}
        ;; items:
        (dom/li #js {}
          (dom/button #js {:className "c-dropdown__link"} "Banannas"))))))

(defexample dropdown-right-aligned
  "# Right Aligned Dropdown"
  (let [open (boolean (om/get-state this :open))
        menu-class (str "c-dropdown__menu" (if open " is-active" ""))]
    (dom/div #js {:className "u-end"}
      (dom/div #js {:className "c-dropdown c-dropdown--right"}
        (dom/button #js {:onClick #(toggle-open this) :className "c-dropdown__select c-dropdown__select--right js-dropdown-toggle"} "Right Aligned")
        (dom/ul #js {:id "test-dropdown" :aria-hidden "true" :className menu-class :tabIndex "-1"}
          (dom/li #js {}
            (dom/button #js {:className "c-dropdown__link"} "Apples")))))))

(defexample dropdown-button
  "# Button Dropdown"
  (let [open (boolean (om/get-state this :open))
        menu-class (str "c-dropdown__menu" (if open " is-active" ""))]
    (dom/div #js {:className "c-dropdown"}
      (dom/button #js {:onClick #(toggle-open this) :className "c-button c-button--dropdown js-dropdown-toggle"} "Filter")
      (dom/ul #js {:id "test-dropdown" :aria-hidden "true" :className menu-class :tabIndex "-1"}
        (dom/li #js {}
          (dom/button #js {:className "c-dropdown__link"} "Water"))))))

(defexample dropdown-button-2
  "# Button Dropdown"
  (let [open (boolean (om/get-state this :open))
        menu-class (str "c-dropdown__menu" (if open " is-active" ""))]
    (dom/div #js {:className "o-button-group"}
      (dom/button #js {:className "c-button"} "Dropdown")
      (dom/button #js {:onClick #(toggle-open this) :className "c-button c-button--dropdown"})
      (dom/ul #js {:className menu-class}
        (dom/li #js {}
          (dom/button #js {:className "c-dropdown__link"} "Water"))))))

(defexample dropdown-search-multi
  "# Multi-Select, Searchable Dropdown"
  (let [open (boolean (om/get-state this :open))
        items (mapv #(str "Item " %) (range 1 20))
        menu-class (str "c-dropdown__menu" (if open " is-active" ""))]
    (dom/div #js {:className "c-dropdown c-dropdown--large"}
      (dom/button #js {:onClick #(toggle-open this) :className "c-button c-button--dropdown js-dropdown-toggle"} "Filter")
      (dom/div #js {:id "test-dropdown" :aria-hidden "true" :className menu-class :tabIndex "-1"}
        (dom/div #js {:className "u-trailer--quarter"}
          (dom/input #js {:type "text" :placeholder "Search..." :className "c-input"}))
        (dom/div #js {:className "c-dropdown__viewer"}
          (map (fn [item]
                 (dom/div #js {:key item :className "u-leader--sixth u-trailer--sixth"}
                   (dom/input #js {:type "checkbox" :id (str item "-cb") :className "c-checkbox"})
                   (dom/label #js {:htmlFor (str item "-cb")} item)))
               items))
        (dom/button #js {:onClick #(om/update-state! this assoc :open false) :className "c-button c-button--wide"} "Apply")))))

(defexample dropdown-data
  "## Dropdown Data

  This is a control that is meant to let you view what various dropdowns would show, for example in cases
  of UI that lets you configure UI.

  Note: The dropdown list underneath should not be enabled when the dropdown list is visible. Doing this fosters a better interaction for the user."
  (let [open (boolean (om/get-state this :open))
        name (or (om/get-state this :menu-name) "Menu 1")
        menu-1-items (mapv #(str "Item " %) (range 1 5))
        menu-2-items (mapv #(str "Other " %) (range 1 3))
        menu (or (om/get-state this :menu) menu-1-items)]
    (dom/div #js {:className "c-dropdown c-dropdown--data"}
      (dom/button #js {:onClick   #(toggle-open this)
                       :className "c-dropdown__select js-dropdown-toggle"} name)
      (dom/ul #js {:id        "test-dropdown" :aria-hidden "true"
                   :className (str "c-dropdown__menu" (when open " is-active")) :tabIndex "-1"}
        (dom/li #js {}
          (dom/button #js {:onClick   #(om/update-state! this assoc :open false :menu menu-1-items :menu-name "Menu 1")
                           :className "c-dropdown__link"} "Menu 1"))
        (dom/li #js {}
          (dom/button #js {:onClick   #(om/update-state! this assoc :open false :menu menu-2-items :menu-name "Menu 2")
                           :className "c-dropdown__link"} "Menu 2")))
      (dom/ul #js {:className "c-dropdown__list" :tabIndex "-1"}
        (map (fn [item]
               (dom/li #js {:key item}
                 (dom/button #js {:className "c-dropdown__link"} item))) menu)))))

(defexample field-normal
  "# Regular Fields"
  (dom/div nil
    (dom/input #js {:type "text" :required "true" :placeholder "Required field" :className "c-field"})
    (dom/input #js {:type "text" :placeholder "Optional field" :className "c-field"})))

(defexample field-sizes
  "# Fields of Various Size"
  (dom/div #js {}
    (dom/input #js {:type "text" :className "c-field c-field--small" :placeholder "The quick brown fox..."})
    (dom/input #js {:type "text" :className "c-field" :placeholder "The quick brown fox..."})
    (dom/input #js {:type "text" :className "c-field c-field--medium" :placeholder "The quick brown fox..."})
    (dom/input #js {:type "text" :className "c-field c-field--large" :placeholder "The quick brown fox..."})))

(defexample field-states
  "# Field States"
  (dom/div #js {}
    (dom/input #js {:type "text" :placeholder "FOCUSED" :className "c-field has-focus"})
    (dom/input #js {:type "text" :placeholder "INVALID" :className "c-field is-invalid"})
    (dom/input #js {:type "text" :placeholder "ERROR" :className "c-field is-error"})))

(defexample input-normal
  "# Inputs of Various Type"
  (dom/div nil
    (dom/input #js {:type "text" :className "c-input" :required "true" :placeholder "Required field"})
    (dom/input #js {:type "text" :placeholder "Optional field" :className "c-input"})
    (mapv (fn [typ] (dom/input #js {:type typ :placeholder typ :className "c-input"}))
          ["text" "password" "date" "datetime" "datetime-local" "month" "week" "email" "number" "search" "tel" "time" "url" "color"])))

(defexample input-states
  "# Inputs States"
  (dom/div #js {}
    (dom/input #js {:type "text" :placeholder "FOCUSED" :className "c-input has-focus"})
    (dom/input #js {:type "text" :placeholder "INVALID" :className "c-input is-invalid"})
    (dom/input #js {:type "text" :placeholder "ERROR" :className "c-input is-error"})))

(defexample input-round
  "# Rounded Input with Icons"
  (dom/div #js {:className "u-wrapper"}
    (dom/input #js {:type "text" :className "c-input c-input--round c-input--inset" :placeholder "Search..." :autocorrect "off" :autocapitalize "off" :autocomplete "off" :spellcheck "false"})
    (dom/button #js {:type "submit" :className "c-button c-button--icon u-absolute--top-left" :disabled "" :aria-label "Submit"}
      (dom/span #js {:className "c-icon c-icon--search"}
        (icons/material-icon :search)))
    (dom/button #js {:className "c-button c-button--icon u-absolute--top-right" :aria-label "Close Search"}
      (dom/span #js {:className "c-icon c-icon--cancel"}
        (icons/material-icon :cancel)))))

(defexample input-collapsable
  "# Collapsable Input"
  (let [open? (boolean (om/get-state this :open))]
    (dom/div #js {}
      (dom/button #js {:title     "Open Search"
                       :className (str "c-button c-button--icon" (when open? " u-hide"))
                       :onClick   #(toggle-open this)
                       :type      "submit" :aria-label "Submit"}
        (dom/span #js {:className "c-icon c-icon--search"}
          (icons/material-icon :search)))
      (dom/div #js {:className (str "u-wrapper " (when-not open? " u-hide"))}
        (dom/input #js {:className "c-input"})
        (dom/button #js {:aria-label "Close Search"
                         :onClick    #(toggle-open this)
                         :className  "c-button c-button--icon u-absolute--top-right"}
          (dom/span #js {:className "c-icon c-icon--cancel"}
            (icons/material-icon :search)))))))

(defexample textarea
  "# Text Area"
  (dom/textarea {:className "c-input c-input--multi-line"}))

(defexample input-validation
  "# Input Validation"
  (dom/div #js {}
    (dom/div #js {:className "u-wrapper"}
      (dom/input #js {:type "text" :className "c-input has-focus"})
      (dom/div #js {:className "c-input__validation"} "Validated message"))
    (dom/div #js {:className "u-wrapper"}
      (dom/input #js {:type "text" :className "c-input"})
      (dom/div #js {:className "c-input__validation c-input__validation--neutral"} "Neutral validated message"))
    (dom/div #js {:className "u-wrapper"}
      (dom/input #js {:type "email" :value "notauser at clientcom" :required "" :className "c-input"})
      (dom/div #js {:className "c-input__validation c-input__validation--warning"} "Warning validated message"))
    (dom/div #js {:className "u-wrapper"}
      (dom/input #js {:type "text" :placeholder "Placeholder text" :className "c-input is-error"})
      (dom/div #js {:className "c-input__validation c-input__validation--error"} "Error validated message"))))

(defexample icons
  "# Basic Icon"
  (dom/span #js {:className "c-icon c-icon--large"}
    (icons/material-icon :timer)))

(defexample icon-sizes
  "# Icon Sizes"
  (let [sizes ["--small" "" "--medium" "--large" "--xlarge" "--huge"]]
    (dom/div #js {}
      (mapv (fn [sz]
              (dom/figure #js {}
                (dom/span #js {:className (str "c-icon c-icon" sz)}
                  (icons/material-icon :alarm))
                (dom/figcaption #js {} (str ".c-icon" sz)))
              ) sizes))))

(defexample icon-colors
  "# Icon Colors"
  (let [colors ["positive" "informative" "neutral" "live" "alterable" "negative"]]
    (dom/div #js {}
      (mapv (fn [color]
              (dom/figure #js {}
                (dom/span #js {:className (str "c-icon is-" color)}
                  (icons/material-icon :alarm))
                (dom/figcaption #js {} (str "is-" color)))
              ) colors))))

(defexample icon-states
  "# Icon States"
  (let [states ["active" "passive" "selectable" "disabled"]]
    (dom/div #js {}
      (mapv (fn [state]
              (dom/figure #js {}
                (dom/span #js {:className (str "c-icon c-icon--large is-" state)}
                  (icons/material-icon :alarm))
                (dom/figcaption #js {} (str "is-" state)))
              ) states))))

(defexample available-icons
  "# All Available Icons `(untangled.icons/material-icon :name)`

  NOTE: Some icons have an additonal CSS set of rules in this style kit, so it
  is recommended that you wrap icons with c-icon-{iconname}."
  (dom/div #js {}
    (mapv (fn [nm]
            (dom/figure #js {}
              (dom/span #js {:className (str "c-icon c-icon-" nm)}
                (icons/material-icon nm))
              (dom/figcaption #js {} (str nm)))
            ) icons/icon-names)))

(defexample labels
  "# Label Types"
  (dom/div #js {}
    (dom/span #js {:className "c-label"} "Default")
    (dom/span #js {:className "c-label c-label--positive"} "Positive")
    (dom/span #js {:className "c-label c-label--informative"} "Informative")
    (dom/span #js {:className "c-label c-label--informative-alt"} "Informative Alt")
    (dom/span #js {:className "c-label c-label--neutral"} "Neutral")
    (dom/span #js {:className "c-label c-label--live"} "Live")
    (dom/span #js {:className "c-label c-label--alterable"} "Alterable")
    (dom/span #js {:className "c-label c-label--negative"} "Negative")))

(defexample label-icons
  "# Labels with Icons"
  (dom/div #js {}
    (dom/span #js {:className "c-label c-label--positive"}
      (dom/span #js {:className "c-icon"}
        (icons/material-icon :add)) " Add ")
    (dom/span #js {:className "c-label c-label--negative"}
      (dom/span #js {:className "c-icon"}
        (icons/material-icon :close)) " Remove ")))

(defexample loader
  "# Loader"
  (dom/div #js {:style #js {:height "100px"}}
    (dom/div #js {:className "c-loader"})
    (dom/div #js {:className "c-loader c-loader--neutral"})))

(defexample block-menu
  "# Block Menu"
  (dom/ul #js {:className "c-menu c-menu--block"}
    (dom/li #js {}
      (dom/button #js {:className "c-menu__link"} "Widgets"))
    (dom/li #js {}
      (dom/button #js {:className "c-menu__link"} "Doodads"))
    (dom/li #js {}
      (dom/button #js {:className "c-menu__link"} "Apparatuses"))
    (dom/li #js {}
      (dom/button #js {:className "c-menu__link has-descendants is-active"} "Things")
      (dom/ul #js {:className "c-menu__submenu"}
        (dom/li #js {}
          (dom/button #js {:className "c-menu__link"} "Thingamabob"))
        (dom/li #js {}
          (dom/button #js {:className "c-menu__link"} "Thingamajig"))
        (dom/li #js {}
          (dom/button #js {:className "c-menu__link"} "Thinger"))))))

(defexample inline-menu
  "# Inline Menu"
  (dom/ul #js {:className "c-menu c-menu--inline"}
    (dom/li #js {}
      (dom/button #js {:className "c-menu__link is-active"} "Link 1"))
    (dom/li #js {}
      (dom/button #js {:className "c-menu__link"} "Link 2"))
    (dom/li #js {}
      (dom/button #js {:className "c-menu__link"} "Link 3"))))

(defexample messages
  "# Messages"
  (dom/div #js {}
    (dom/div #js {:className "c-message"} "This is a message")
    (dom/div #js {:className "c-message--neutral"} "This is a neutral message")
    (dom/div #js {:className "c-message--alert"} "This is an alert message")
    (dom/div #js {:className "c-message--success"} "This is a successful message")
    (dom/div #js {:className "c-message--warning"} "This is a warning message")))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; START OF SECTIONS (within a feature set...e.g. components)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; NOTE: This is where you add the sections for index
(def sections [
               ; NOTE: :examples is a list of example names, rendered in order given
               {:id :messages :title "Messages" :examples [messages]}
               {:id :menus :title "Menus" :examples [block-menu inline-menu]}
               {:id            :loader :title "Loader" :examples [loader]
                :documentation "Webapps often need to provide feedback to the user for when things are loading, so we have a few loader components that are animated using only CSS techniques."}

               {:id :labels :title "Labels" :examples [labels label-icons]}
               {:id            :badges :title "Badges"
                :documentation "
                ## Badges?

                we don't need no stinkin badges!"
                :examples      [badge-example-1 badge-on-button badge-with-icon]}
               {:id :buttons :title "Buttons" :examples [button-size-and-shape button-color button-state button-postfix]}
               {:id :card :title "Card" :examples [drop-card active-card inactive-card card-with-titlebar card-example rounded-card transparent-card ruled-card]}
               {:id :checkboxes :title "Checkboxes" :examples [checkboxes]}
               {:id :dropdowns :title "Dropdowns" :examples [dropdown dropdown-large dropdown-alterable dropdown-negative dropdown-positive dropdown-right-aligned dropdown-button-2 dropdown-button dropdown-search-multi dropdown-data]}
               {:id :fields :title "Fields" :examples [field-normal field-states field-sizes]}
               {:id       :inputs :title "Form Inputs"
                :documentation
                          "Input class give support for visualizing various kind of interactions.
                          Supported input types are: `text`, `password`, `date`, `datetime`,
                          `datetime-local`, `month`, `week`, `email`, `number`, `search`, `tel`, `time`, `url`, `color`.
                          ```"
                :examples [input-normal input-states input-round input-collapsable textarea input-validation]}
               {:id       :icons :title "Icons" :documentation
                          "The preferred icon library is Google's <a href='https://design.google.com/icons/'>Material icons</a>. We include the entire library in the UI Components project in the form of svg paths that get inlined into your markup.

                           Use these icon classes on `<span>` elements wrapping your inline svg icons. Here is a simple icon in it's purest form."
                :examples [icons icon-sizes icon-colors icon-states available-icons]}
               ])

;; NOTE: Rendering of the main guide UI
(defui UI
  Object
  (render [this]
    (dom/div nil
      (util/section-index sections)
      (mapv (fn [k] (util/section k sections)) (map :id sections)))))

