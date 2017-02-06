(defproject untangled-stylekit "0.1.0-SNAPSHOT"
  :description "A PostCSS Style Kit with Direct Untangled Web Framework Support"
  :license {:name "MIT" :url "https://opensource.org/licenses/MIT"}

  :dependencies [[org.clojure/clojure "1.9.0-alpha14" :scope "provided"]
                 [org.clojure/clojurescript "1.9.293" :scope "provided"]]

  :plugins [[lein-cljsbuild "1.1.4"]]

  :source-paths ["dev" "src/main/cards" "src/main/clj"]
  :clean-targets ^{:protect false} ["resources/public/js"]

  :figwheel {:css-dirs ["resources/public/css"]}

  :cljsbuild {:builds [{:id           "cards"
                        :figwheel     true
                        :source-paths ["dev" "src/main/cards" "src/main/clj"]
                        :compiler     {:asset-path           "js/cards"
                                       :optimizations        :none
                                       :recompile-dependents true
                                       :main                 cljs.user
                                       :output-dir           "resources/public/js/cards"
                                       :output-to            "resources/public/js/cards.js"
                                       :preloads             [devtools.preload]
                                       :source-map-timestamp true}}]}

  :profiles {:dev {:dependencies [[org.omcljs/om "1.0.0-alpha47"]
                                  [navis/untangled-client "0.6.1-SNAPSHOT"]
                                  [hickory "0.7.0"]
                                  [binaryage/devtools "0.8.3"]
                                  [org.clojure/tools.namespace "0.2.11"]
                                  [com.cemerick/piggieback "0.2.1"]
                                  [figwheel-sidecar "0.5.8" :exclusions [org.clojure/tools.reader]]
                                  [devcards "0.2.2" :exclusions [org.omcljs/om]]
                                  [venantius/glow "0.1.4"]]
                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}}})
