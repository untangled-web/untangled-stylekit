(ns styles.getting-started
  (:require-macros user)
  (:require [om.next :as om :refer-macros [defui]]
    ;; NOTE: This is where the interesting macros and such are at:
            [styles.util :as util
             :refer [to-cljs]
             :refer-macros [source->react defexample defarticle]]
            [om.dom :as dom]))

(defarticle intro
  "# Getting Started

  Stylekit comes in a few different flavors to fit your needs. Please choose a flavor and follow the guide to get started.

  **Components**

  Browse our pre-built components to get a feel for how well all of them work together. [Learn more](#asd)

  # Boo

  **CSS**

  You can get straight to the point and get a simple CSS file with only what you need.

  **PostCSS**

  Style like a pro with future spec-compliant CSS that lets you write variables, applys and nesting in your CSS. This is the easiest way to modify and extend stylekit.
  ")

(defexample CSS
  "# Using CSS"
  (dom/span nil))

(defexample PostCSS
  "# Using PostCSS"
  (dom/span nil))

(def sections
  (vec [;; NOTE: :examples is a list of example names, rendered in order given
        {:id :settings-config :title "Intro" :examples [intro]}
        {:id :settings-config :title "Using CSS" :examples [CSS]}
        {:id :settings-config :title "Using PostCSS" :examples [PostCSS]}

        ]))

