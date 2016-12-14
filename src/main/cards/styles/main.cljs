(ns styles.main
  (:require [styles.components]
            [styles.elements]
            [styles.objects]
            [styles.utilities]
            [untangled.client.core :as uc]
            [styles.util :as util]))

(def Client (uc/new-untangled-client))
(uc/mount Client util/HTMLConverterApp "example-1")

(def Client2 (uc/new-untangled-client))
(uc/mount Client2 styles.components/UI "example-2")

(def Client3 (uc/new-untangled-client))
(uc/mount Client3 styles.elements/UI "example-3")

(def Client5 (uc/new-untangled-client))
(uc/mount Client5 styles.objects/UI "example-5")

(def Client-utilities (uc/new-untangled-client))
(uc/mount Client-utilities styles.utilities/UI "example-utilities")


