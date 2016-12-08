(ns styles.cards
  (:require-macros user)
  (:require [om.next :as om :refer-macros [defui]]
            [untangled.client.core :as uc]
            [styles.util :as util :refer [source->react to-cljs defexample]]
            [om.dom :as dom]
            [devcards.util.edn-renderer :as edn]
            [untangled.client.mutations :as m]))

;; USING HTML CONVERTER:
(def Client (uc/new-untangled-client))
(uc/mount Client util/HTMLConverterApp "example-1")

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

(def sections [
               {:id :badges :title "Badges" :examples [badge-example-1 badge-on-button badge-with-icon]}
               {:id :buttons :title "Buttons" :examples [button-size-and-shape button-color button-state]}
               ])

(defn section
  [id sections]
  (let [{:keys [title examples]} (first (filter #(= id (:id %)) sections))]
    (dom/div nil
      (dom/a #js {:id id}
        (dom/h1 nil title))
      (map #(%) examples))))

(defn section-index [sections]
  (dom/ul nil
    (map #(dom/li nil (dom/a #js {:href (str "#" (:id %))} (:title %))) sections)))

(defui UI
  Object
  (render [this]
    (dom/div nil
      (section-index sections)
      (section :badges sections)
      (section :buttons sections))))

(def Client2 (uc/new-untangled-client))
(uc/mount Client2 UI "example-2")
