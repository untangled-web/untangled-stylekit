(ns styles.main
  (:require [styles.components]
            [untangled.client.core :as uc]
            [styles.util :as util]))

;; NOTE: to embed an HTML CONVERTER on div with id "example-1":
(def Client (uc/new-untangled-client))
(uc/mount Client util/HTMLConverterApp "example-1")

;; NOTE: Mount the main UI on div with ID example-2
(def Client2 (uc/new-untangled-client))
(uc/mount Client2 styles.components/UI "example-2")


