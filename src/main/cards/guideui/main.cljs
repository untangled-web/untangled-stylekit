(ns guideui.main
  (:require [styles.components]
            [styles.elements]
            [styles.objects]
            [styles.utilities]
            [untangled.client.core :as uc]
            guideui.ui))

;(def Client (uc/new-untangled-client))
;(uc/mount Client util/HTMLConverterApp "example-1")

(defonce ui-client (atom (uc/new-untangled-client)))

