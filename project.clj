(defproject sample-reagent "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [reagent "0.7.0"]
                 [re-com "0.8.0"]
                 [org.clojure/clojurescript "1.9.946"]]

  :source-paths ["src/clj"]

  :plugins [[lein-cljsbuild "1.1.7"  :exclusions [[org.clojure/clojure]]]
            [lein-figwheel "0.5.13"]]

  :figwheel {:css-dirs ["resources/public/css"]}

  :clean-targets ^{:protect false} ["resources/public/js/compiled" :target-path]

  :cljsbuild
  {:builds
   [{:id "dev"
     :source-paths ["src/cljs"]
     :figwheel {:on-jsload "sample-reagent.core/on-js-reload"
                :open-urls ["http://localhost:3449/index.html"]}
     :compiler {:main sample-reagent.core
                :asset-path "js/compiled/out"
                :output-to "resources/public/js/compiled/sample_reagent.js"
                :output-dir "resources/public/js/compiled/out"
                :source-map-timestamp true
                :preloads [devtools.preload]
                :externs ["keymaster-externs.js"]}}]}

 :profiles {:dev
             {:dependencies [[binaryage/devtools "0.9.4"]
                             [figwheel-sidecar "0.5.13"]
                             [com.cemerick/piggieback "0.2.2"]
                             [org.clojure/tools.nrepl "0.2.13"]]

              :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}}})
