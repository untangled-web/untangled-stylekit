(ns styles.utilities
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

;; Grid Examples

(def grid-header (devcards.core/markdown->react "Grids are the bread and butter of web design, this one is based on flexbox. Needless to say you will find this grid system a joy to use. [Flexbox Mythbusting](http://jonyablonski.com/2015/flexbox-myth-busting/)\n\n
#### New to grids?\n\n
Check out my presentation on CSS Grids can help you, complete with a [slide deck](https://speakerdeck.com/stephenway/css-grids-can-help-you), and a [CodePen](http://codepen.io/stephenway/pen/dMKzvy) to play with complex grid layouts."))


(defexample grid-example-1
  "## A 12 column grid"
  (dom/div #js {:className "u-row"}
           (dom/div #js {:className "u-column--1"}
                    (dom/div #js {:className "box-row box-tall"} "1"))
           (dom/div #js {:className "u-column--1"}
                    (dom/div #js {:className "box-row box-tall"} "2"))
           (dom/div #js {:className "u-column--1"}
                    (dom/div #js {:className "box-row box-tall"} "3"))
           (dom/div #js {:className "u-column--1"}
                    (dom/div #js {:className "box-row box-tall"} "4"))
           (dom/div #js {:className "u-column--1"}
                    (dom/div #js {:className "box-row box-tall"} "5"))
           (dom/div #js {:className "u-column--1"}
                    (dom/div #js {:className "box-row box-tall"} "6"))
           (dom/div #js {:className "u-column--1"}
                    (dom/div #js {:className "box-row box-tall"} "7"))
           (dom/div #js {:className "u-column--1"}
                    (dom/div #js {:className "box-row box-tall"} "8"))
           (dom/div #js {:className "u-column--1"}
                    (dom/div #js {:className "box-row box-tall"} "9"))
           (dom/div #js {:className "u-column--1"}
                    (dom/div #js {:className "box-row box-tall"} "10"))
           (dom/div #js {:className "u-column--1"}
                    (dom/div #js {:className "box-row box-tall"} "11"))
           (dom/div #js {:className "u-column--1"}
                    (dom/div #js {:className "box-row box-tall"} "12"))))

(def learn-header (devcards.core/markdown->react "This group of select boxes will show you how columns adapt to their siblings when they occupy more or less columns."))

;TODO: learn switchers are not working as included here. Need to add actions.
(defexample learn-example
  ""                                                        ;"## Learn"
  (dom/div #js {:className "u-row o-column-switcher-row"}
           (dom/div #js {:className "u-column--4"}
                    (dom/select #js {:className "o-column-switcher"}
                                (dom/option #js {} "u-column--1")
                                (dom/option #js {} "u-column--2")
                                (dom/option #js {} "u-column--3")
                                (dom/option #js {} "u-column--4")
                                (dom/option #js {} "u-column--5")
                                (dom/option #js {} "u-column--6")
                                (dom/option #js {} "u-column--7")
                                (dom/option #js {} "u-column--8")
                                (dom/option #js {} "u-column--9")
                                (dom/option #js {} "u-column--10")
                                (dom/option #js {} "u-column--11")
                                (dom/option #js {} "u-column--12")))
           (dom/div #js {:className "u-column--8"}
                    (dom/select #js {:className "o-column-switcher"}
                                (dom/option #js {} "u-column--1")
                                (dom/option #js {} "u-column--2")
                                (dom/option #js {} "u-column--3")
                                (dom/option #js {} "u-column--4")
                                (dom/option #js {} "u-column--5")
                                (dom/option #js {} "u-column--6")
                                (dom/option #js {} "u-column--7")
                                (dom/option #js {} "u-column--8")
                                (dom/option #js {} "u-column--9")
                                (dom/option #js {} "u-column--10")
                                (dom/option #js {} "u-column--11")
                                (dom/option #js {} "u-column--12")))))

(defexample grid-example-3
  "## Autopilot"
  (dom/div #js {}
           (dom/div #js {:className "u-row"}
                    (dom/div #js {:className "u-column"}
                             (dom/div #js {:className "box-row"} "auto"))
                    (dom/div #js {:className "u-column"}
                             (dom/div #js {:className "box-row"} "auto")))
           (dom/div #js {:className "u-row"}
                    (dom/div #js {:className "u-column"}
                             (dom/div #js {:className "box-row"} "auto"))
                    (dom/div #js {:className "u-column"}
                             (dom/div #js {:className "box-row"} "auto"))
                    (dom/div #js {:className "u-column"}
                             (dom/div #js {:className "box-row"} "auto")))))

(defexample grid-example-4
  "## Responsive"
  (dom/div #js {}
           (dom/div #js {:className "u-row"}
                    (dom/div #js {:className "u-column--3@sm u-column--2@md u-column--1@lg"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {:className "u-show@sm"} "3")
                                      (dom/span #js {:className "u-show@md"} "2")
                                      (dom/span #js {:className "u-show@lg"} "1")))
                    (dom/div #js {:className "u-column--6@sm u-column--8@md u-column--10@lg"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {:className "u-show@sm"} "6")
                                      (dom/span #js{:className "u-show@md"} "8")
                                      (dom/span #js {:className "u-show@lg"} "10")))
                    (dom/div #js{:className "u-column--3@sm u-column--2@md u-column--1@lg"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {:className "u-show@sm"} "3")
                                      (dom/span #js {:className "u-show@md"} "2")
                                      (dom/span #js {:className "u-show@lg"} "1"))))
           (dom/div #js {:className "u-row"}
                    (dom/div #js {:className "u-column--3@sm u-column--2@md u-column--1@lg"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {:className "u-show@sm"} "3")
                                      (dom/span #js {:className "u-show@md"} "2")
                                      (dom/span #js {:className "u-show@lg"} "1")))
                    (dom/div #js {:className "u-column--9@sm u-column--10@md u-column--11@lg"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {:className "u-show@sm"} "9")
                                      (dom/span #js {:className "u-show@md"} "10")
                                      (dom/span #js {:className "u-show@lg"} "11"))))
           (dom/div #js {:className "u-row"}
                    (dom/div #js {:className "u-column--6@sm u-column--8@md u-column--10@lg"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js{:className "u-show@sm"} "6")
                                      (dom/span #js {:className "u-show@md"} "8")
                                      (dom/span #js{:className "u-show@lg"} "10")))
                    (dom/div #js {:className "u-column--6@sm u-column--4@md u-column--2@lg"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {:className "u-show@sm"} "6")
                                      (dom/span #js {:className "u-show@md"} "4")
                                      (dom/span #js {:className "u-show@lg"} "2"))))))

(defexample grid-example-5
  "## Fluid"
  (dom/div #js {}
           (dom/div #js {:className "u-row"}
                    (dom/div #js {:className "u-column--12"}
                             (dom/div #js{:className "box-row"}
                                      (dom/span #js {} "12"))))
           (dom/div #js {:className "u-row"}
                    (dom/div #js {:className "u-column--1"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {} "1")) "")
                    (dom/div #js {:className "u-column--11"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {} "11"))))
           (dom/div #js {:className "u-row"}
                    (dom/div #js {:className "u-column--2"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {} "2")))
                    (dom/div #js {:className "u-column--10"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {} "10"))))
           (dom/div #js {:className "u-row"}
                    (dom/div #js {:className "u-column--3"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {} "3")))
                    (dom/div #js {:className "u-column--9"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {} "9"))))
           (dom/div #js {:className "u-row"}
                    (dom/div #js {:className "u-column--4"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {} "4")))
                    (dom/div #js {:className "u-column--8"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {} "8"))))
           (dom/div #js{:className "u-row"}
                    (dom/div #js {:className "u-column--5"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {} "5")))
                    (dom/div #js {:className "u-column--7"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {} "7"))))
           (dom/div #js {:className "u-row"}
                    (dom/div #js {:className "u-column--6"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {} "6")))
                    (dom/div #js{:className "u-column--6"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {} "6"))))
           (dom/div #js {:className "u-row"}
                    (dom/div #js {:className "u-column--7"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {} "7")))
                    (dom/div #js {:className "u-column--5"}
                             (dom/div #js{:className "box-row"}
                                      (dom/span #js {} "5"))))
           (dom/div #js {:className "u-row"}
                    (dom/div #js {:className "u-column--8"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {} "8")))
                    (dom/div #js {:className "u-column--4"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {} "4")) ""))
           (dom/div #js {:className "u-row"}
                    (dom/div #js {:className "u-column--9"}
                             (dom/div #js{:className "box-row"}
                                      (dom/span #js {} "9")))
                    (dom/div #js {:className "u-column--3"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {} "3"))))
           (dom/div #js {:className "u-row"}
                    (dom/div #js {:className "u-column--10"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {} "10")))
                    (dom/div #js {:className "u-column--2"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js{} "2"))))
           (dom/div #js {:className "u-row"}
                    (dom/div #js {:className "u-column--11"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {} "11")))
                    (dom/div #js {:className "u-column--1"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {} "1"))))))

(defexample grid-example-6
  "## Column Push"
  (dom/div #js {}
           (dom/div #js {:className "u-row"}
                    (dom/div #js {:className "u-column--1 u-push--11"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {}
                                                (dom/i #js {:className "c-icon-right-dir"}) " 11"))))
           (dom/div #js {:className "u-row"}
                    (dom/div #js {:className "u-column--2 u-push--10"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {}
                                                (dom/i #js {:className "c-icon-right-dir"}) " 10"))))
           (dom/div #js {:className "u-row"}
                    (dom/div #js {:className "u-column--3 u-push--9"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {}
                                                (dom/i #js {:className "c-icon-right-dir"}) " 9"))))
           (dom/div #js {:className "u-row"}
                    (dom/div #js {:className "u-column--4 u-push--8"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {}
                                                (dom/i #js {:className "c-icon-right-dir"}) " 8"))))
           (dom/div #js {:className "u-row"}
                    (dom/div #js {:className "u-column--5 u-push--7"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {}
                                                (dom/i #js {:className "c-icon-right-dir"}) " 7"))))
           (dom/div #js {:className "u-row"}
                    (dom/div #js {:className "u-column--6 u-push--6"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {}
                                                (dom/i #js {:className "c-icon-right-dir"}) " 6"))))
           (dom/div #js {:className "u-row"}
                    (dom/div #js {:className "u-column--7 u-push--5"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {}
                                                (dom/i #js {:className "c-icon-right-dir"}) " 5"))))
           (dom/div #js {:className "u-row"}
                    (dom/div #js {:className "u-column--8 u-push--4"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {}
                                                (dom/i #js {:className "c-icon-right-dir"}) " 4"))))
           (dom/div #js {:className "u-row"}
                    (dom/div #js {:className "u-column--9 u-push--3"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {}
                                                (dom/i #js {:className "c-icon-right-dir"}) " 3"))))
           (dom/div #js {:className "u-row"}
                    (dom/div #js {:className "u-column--10 u-push--2"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {}
                                                (dom/i #js {:className "c-icon-right-dir"}) " 2"))))
           (dom/div #js {:className "u-row"}
                    (dom/div #js {:className "u-column--11 u-push--1"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {}
                                                (dom/i #js {:className "c-icon-right-dir"}) " 1"))))))

(defexample grid-example-7
  "## Column Pull"
  (dom/div #js {}
           (dom/div #js {:className "u-row"}
                    (dom/div #js {:className "u-column--1 u-pull--11"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {}
                                                (dom/i #js {:className "c-icon-right-dir"}) " 11"))))
           (dom/div #js {:className "u-row"}
                    (dom/div #js {:className "u-column--2 u-pull--10"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {}
                                                (dom/i #js {:className "c-icon-right-dir"}) " 10"))))
           (dom/div #js {:className "u-row"}
                    (dom/div #js {:className "u-column--3 u-pull--9"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {}
                                                (dom/i #js {:className "c-icon-right-dir"}) " 9"))))
           (dom/div #js {:className "u-row"}
                    (dom/div #js {:className "u-column--4 u-pull--8"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {}
                                                (dom/i #js {:className "c-icon-right-dir"}) " 8"))))
           (dom/div #js {:className "u-row"}
                    (dom/div #js {:className "u-column--5 u-pull--7"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {}
                                                (dom/i #js {:className "c-icon-right-dir"}) " 7"))))
           (dom/div #js {:className "u-row"}
                    (dom/div #js {:className "u-column--6 u-pull--6"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {}
                                                (dom/i #js {:className "c-icon-right-dir"}) " 6"))))
           (dom/div #js {:className "u-row"}
                    (dom/div #js {:className "u-column--7 u-pull--5"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {}
                                                (dom/i #js {:className "c-icon-right-dir"}) " 5"))))
           (dom/div #js {:className "u-row"}
                    (dom/div #js {:className "u-column--8 u-pull--4"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {}
                                                (dom/i #js {:className "c-icon-right-dir"}) " 4"))))
           (dom/div #js {:className "u-row"}
                    (dom/div #js {:className "u-column--9 u-pull--3"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {}
                                                (dom/i #js {:className "c-icon-right-dir"}) " 3"))))
           (dom/div #js {:className "u-row"}
                    (dom/div #js {:className "u-column--10 u-pull--2"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {}
                                                (dom/i #js {:className "c-icon-right-dir"}) " 2"))))
           (dom/div #js {:className "u-row"}
                    (dom/div #js {:className "u-column--11 u-pull--1"}
                             (dom/div #js {:className "box-row"}
                                      (dom/span #js {}
                                                (dom/i #js {:className "c-icon-right-dir"}) " 1"))))))

(defexample grid-example-8
  "## Column Push/Pull"
  (dom/div #js {:className "u-row"}
           (dom/div #js {:className "u-column--2 u-push--5 u-pull--5"}
                    (dom/div #js {:className "box-row"} ".u-column--2"
                             (dom/br #js {}) ".u-push--5"
                             (dom/br #js {}) ".u-pull--5"))
           (dom/div #js {:className "u-column--4 u-push--4 u-pull--4"}
                    (dom/div #js {:className "box-row"} ".u-column--4"
                             (dom/br #js {}) ".u-push--4.u-pull--4"))
           (dom/div #js {:className "u-column--6 u-push--3 u-pull--3"}
                    (dom/div #js {:className "box-row"} ".u-column--6.u-push--3.u-pull--3"))
           (dom/div #js {:className "u-column--8 u-push--2 u-pull--2"}
                    (dom/div #js {:className "box-row"} ".u-column--8.u-push--2.u-pull--2"))
           (dom/div #js {:className "u-column--10 u-push--1 u-pull--1"}
                    (dom/div #js {:className "box-row"} ".u-column--10.u-push--1.u-pull--1"))))

(defexample grid-example-9
  "## Nesting"
  (dom/div #js {}
           (dom/div #js {:className "u-row"}
                    (dom/div #js {:className "u-column--4"}
                             (dom/div #js {:className "box box-container"}
                                      (dom/div #js {:className "u-row"}
                                               (dom/div #js {:className "u-column--12"}
                                                        (dom/div #js {:className "box-first box-container"}
                                                                 (dom/div #js {:className "u-row"}
                                                                          (dom/div #js {:className "u-column--6"}
                                                                                   (dom/div #js {:className "box-nested"}))
                                                                          (dom/div #js {:className "u-column--6"}
                                                                                   (dom/div #js {:className "box-nested"}))))))))
                    (dom/div #js {:className "u-column--8"}
                             (dom/div #js {:className "box box-container"}
                                      (dom/div #js {:className "u-row"}
                                               (dom/div #js {:className "u-column--12"}
                                                        (dom/div #js {:className "box-first box-container"}
                                                                 (dom/div #js {:className "u-row"}
                                                                          (dom/div #js {:className "u-column--3"}
                                                                                   (dom/div #js {:className "box-nested"}))
                                                                          (dom/div #js {:className "u-column--6"}
                                                                                   (dom/div #js {:className "box-nested"}))
                                                                          (dom/div #js {:className "u-column--3"}
                                                                                   (dom/div #js {:className "box-nested"})))))))))))

(defexample grid-example-align-start
  "## Align - Start"
  (dom/div #js {:className "u-row"}
           (dom/div #js {:className "u-column--12"}
                    (dom/div #js {:className "box box-container"}
                             (dom/div #js {:className "u-row u-start"}
                                      (dom/div #js {:className "u-column--6"}
                                               (dom/div #js {:className "box-nested"} " .u-row.u-start ")))))))

(defexample grid-example-align-center
  "## Align - Center"
  (dom/div #js {:className "u-row"}
           (dom/div #js {:className "u-column--12"}
                    (dom/div #js {:className "box box-container"}
                             (dom/div #js {:className "u-row u-center"}
                                      (dom/div #js {:className "u-column--8"}
                                               (dom/div #js {:className "box-nested"} " .u-row.u-center ")))))))

(defexample grid-example-align-end
  "## Align - End"
  (dom/div #js {:className "u-row"}
           (dom/div #js {:className "u-column--12"}
                    (dom/div #js {:className "box box-container"}
                             (dom/div #js {:className "u-row u-end"}
                                      (dom/div #js {:className "u-column--4"}
                                               (dom/div #js {:className "box-nested"} " .u-row.u-end ")))))))

(defexample grid-example-align-top
  "## Align - Top"
  (dom/div #js {:className "u-row u-top"}
           (dom/div #js {:className "u-column--6"}
                    (dom/div #js {:className "box--large"} ".u-row.u-top"))
           (dom/div #js {:className "u-column--6"}
                    (dom/div #js {:className "box"}))))

(defexample grid-example-align-middle
  "## Align - Middle"
  (dom/div #js {:className "u-row u-middle"}
           (dom/div #js {:className "u-column--6"}
                    (dom/div #js {:className "box--large"} ".u-row.u-middle"))
           (dom/div #js {:className "u-column--6"}
                    (dom/div #js {:className "box"}))))

(defexample grid-example-align-bottom
  "## Align - Bottom"
  (dom/div #js {:className "u-row u-bottom"}
           (dom/div #js {:className "u-column--6"}
                    (dom/div #js {:className "box--large"} ".u-row.u-bottom"))
           (dom/div #js {:className "u-column--6"}
                    (dom/div #js {:className "box"}))))

(defexample grid-example-distributed-around
  "## Distributed - Around"
  (dom/div #js {:className "u-row"}
           (dom/div #js {:className "u-column--12"}
                    (dom/div #js {:className "box box-container"}
                             (dom/div #js {:className "u-row u-around"}
                                      (dom/div #js {:className "u-column--2"}
                                               (dom/div #js {:className "box-nested"} "2"))
                                      (dom/div #js {:className "u-column--2"}
                                               (dom/div #js {:className "box-nested"} "2"))
                                      (dom/div #js {:className "u-column--2"}
                                               (dom/div #js {:className "box-nested"} "2")))))))

(defexample grid-example-distributed-between
  "## Distributed - Between"
  (dom/div #js {:className "u-row"}
           (dom/div #js {:className "u-column--12"}
                    (dom/div #js {:className "box box-container"}
                             (dom/div #js {:className "u-row u-between"}
                                      (dom/div #js {:className "u-column--2"}
                                               (dom/div #js {:className "box-nested"} "2"))
                                      (dom/div #js {:className "u-column--2"}
                                               (dom/div #js {:className "box-nested"} "2"))
                                      (dom/div #js {:className "u-column--2"}
                                               (dom/div #js {:className "box-nested"} "2")))))))

(defexample grid-example-ordering-first
  "## Ordering - First"
  (dom/div #js {:className "u-row"}
           (dom/div #js {:className "u-column--12"}
                    (dom/div #js {:className "box box-container"}
                             (dom/div #js {:className "u-row"}
                                      (dom/div #js {:className "u-column--2"}
                                               (dom/div #js {:className "box-first"} "1"))
                                      (dom/div #js {:className "u-column--2"}
                                               (dom/div #js {:className "box-first"} "2"))
                                      (dom/div #js {:className "u-column--2"}
                                               (dom/div #js {:className "box-first"} "3"))
                                      (dom/div #js {:className "u-column--2"}
                                               (dom/div #js {:className "box-first"} "4"))
                                      (dom/div #js {:className "u-column--2"}
                                               (dom/div #js {:className "box-first"} "5"))
                                      (dom/div #js {:className "u-column--2 u-first"}
                                               (dom/div #js {:className "box-nested"} "6")))))))

(defexample grid-example-ordering-last
  "## Ordering - Last"
  (dom/div #js {:className "u-row"}
           (dom/div #js {:className "u-column--12"}
                    (dom/div #js {:className "box box-container"}
                             (dom/div #js {:className "u-row"}
                                      (dom/div #js {:className "u-column--2 u-last"}
                                               (dom/div #js {:className "box-nested"} "1"))
                                      (dom/div #js {:className "u-column--2"}
                                               (dom/div #js {:className "box-first"} "2"))
                                      (dom/div #js {:className "u-column--2"}
                                               (dom/div #js {:className "box-first"} "3"))
                                      (dom/div #js {:className "u-column--2"}
                                               (dom/div #js {:className "box-first"} "4"))
                                      (dom/div #js {:className "u-column--2"}
                                               (dom/div #js {:className "box-first"} "5"))
                                      (dom/div #js {:className "u-column--2"}
                                               (dom/div #js {:className "box-first"} "6")))))))

(defexample grid-example-ordering-reverse
  "## Ordering - Reverse"
  (dom/div #js {:className "u-row"}
           (dom/div #js {:className "u-column--12"}
                    (dom/div #js {:className "box box-container"}
                             (dom/div #js {:className "u-row u-row--reverse"}
                                      (dom/div #js {:className "u-column--2"}
                                               (dom/div #js {:className "box-first"} "1"))
                                      (dom/div #js {:className "u-column--2"}
                                               (dom/div #js {:className "box-first"} "2"))
                                      (dom/div #js {:className "u-column--2"}
                                               (dom/div #js {:className "box-first"} "3"))
                                      (dom/div #js {:className "u-column--2"}
                                               (dom/div #js {:className "box-first"} "4"))
                                      (dom/div #js {:className "u-column--2"}
                                               (dom/div #js {:className "box-first"} "5"))
                                      (dom/div #js {:className "u-column--2"}
                                               (dom/div #js {:className "box-first"} "6")))))))

;TODO: What can we do something with these css "@media" items?
(defexample media-example-breakpoints-flexible
  "## Media - Flexible Breakpoints"
  (dom/div nil nil))

(defexample media-example-breakpoints-fixed
  "## Media - Fixed Breakpoints"
  (dom/div nil nil))

(defexample media-example-breakpoints-orientation
  "## Media - Orientation Breakpoints"
  (dom/div nil nil))

(defexample media-example-breakpoints-media-type
  "## Media - Media Type Breakpoints"
  (dom/div nil nil))

;; Positioning Examples

(defexample positioning-example-fixed
  "## Positioning - Fixed Classes"
  (dom/div #js {}
           (dom/div #js {:className "u-fixed--top"})
           (dom/div #js {:className "u-fixed--top-center"})
           (dom/div #js {:className "u-fixed--top-right"})
           (dom/div #js {:className "u-fixed--top-left"})
           (dom/div #js {:className "u-fixed--bottom"})
           (dom/div #js {:className "u-fixed--bottom-right"})
           (dom/div #js {:className "u-fixed--bottom-left"})
           (dom/div #js {:className "u-fixed--middle"})
           (dom/div #js {:className "u-fixed--middle-center"})))

(defexample positioning-example-absolute
  "## Positioning - Absolute Classes"
(dom/div #js {}
(dom/div #js {:className "u-absolute--top"})
(dom/div #js {:className "u-absolute--top-right"})
(dom/div #js {:className "u-absolute--top-left"})
(dom/div #js {:className "u-absolute--bottom"})
(dom/div #js {:className "u-absolute--bottom-right"})
(dom/div #js {:className "u-absolute--bottom-left"})
(dom/div #js {:className "u-absolute--middle"})
(dom/div #js {:className "u-absolute--middle-center"})))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; START OF SECTIONS (within a feature set...e.g. components)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; NOTE: This is where you add the sections for index

;TODO: Need to determine how markup should be added if Stephen's layout with text under the titles and multiple
; levels are desired. The below shows a couple ideas based upon the "header" idea, but this isn't a good match so
; I'm waiting to modify the rest until we decide our target.

(def sections [
               ; NOTE: :examples is a list of example names, rendered in order given
               {:id :grids :title "Grid" :header grid-header :examples [grid-example-1]}
               {:id :grids-learn :title "Learn" :header learn-header :examples [learn-example
                                                                                grid-example-3 grid-example-4
                                                                                grid-example-5 grid-example-6 grid-example-7 grid-example-8
                                                                                grid-example-9
                                                                                grid-example-align-start grid-example-align-center
                                                                                grid-example-align-end grid-example-align-top
                                                                                grid-example-align-middle grid-example-align-bottom
                                                                                grid-example-distributed-around grid-example-distributed-between
                                                                                grid-example-ordering-first grid-example-ordering-last
                                                                                grid-example-ordering-reverse]}
               {:id :positioning :title "Positioning" :header nil :examples [positioning-example-fixed
                                                                       positioning-example-absolute]}
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
             (section :grids sections)
             (section :grids-learn sections)
             (section :positioning sections)
             )))


