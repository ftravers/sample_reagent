(defproject sample-reagent "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.9.0"]
                 [reagent "0.8.1"]
                 [re-com "2.1.0"]
                 [org.clojure/core.async "0.4.474"]
                 [org.clojure/clojurescript "1.10.339"]
                 [devcards "0.2.5"]
                 [com.bhauman/cljs-test-display "0.1.1"]
                 [com.datomic/datomic-pro "0.9.5561.62" :exclusions [com.google.guava/guava]]
                 [com.grammarly/omniconf "0.3.2"]
                 [integrant "0.7.0"]]

  :plugins [[lein-cljsbuild "1.1.7"  :exclusions [[org.clojure/clojure]]]
            ;; [nrepl/drawbridge "0.1.3"]
            [lein-figwheel "0.5.16"]
            ]

  :profiles {:dev
             {:dependencies [[binaryage/devtools "0.9.4"]
                             [figwheel "0.5.16"]
                             [figwheel-sidecar "0.5.16"]
                             [cider/piggieback "0.3.8"]
                             [org.clojure/tools.nrepl "0.2.13"]
                             [im.chit/vinyasa "0.4.7"]]
              :injections
              [(require '[vinyasa.inject :as inject])
               (inject/in ;; the default injected namespace is `.`
                [clojure.pprint pprint]
                [clojure.repl apropos dir dir-fn doc find-doc pst root-cause source])]

              :repl-options {:nrepl-middleware [cider.piggieback/wrap-cljs-repl]}}}

  :source-paths ["src/cljs"]

  :figwheel {:css-dirs ["resources/public/css"]}

  :clean-targets ^{:protect false} ["resources/public/js/compiled" :target-path]
  
  :cljsbuild
  {:builds
   [{:id "dev"
     :source-paths ["src/cljs" "test/cljs"]
     :figwheel {:on-jsload "sample-reagent.core/on-js-reload"
                :open-urls ["http://localhost:3449/index.html"]}
     :compiler {:main sample-reagent.core
                :asset-path "js/compiled/out"
                :output-to "resources/public/js/compiled/sample_reagent.js"
                :output-dir "resources/public/js/compiled/out"
                :source-map-timestamp true
                :preloads [devtools.preload]}}]})
