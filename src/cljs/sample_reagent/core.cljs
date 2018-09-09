(ns sample-reagent.core
  (:require
   [reagent.core :as reagent]
   [sample-reagent.reusable-components :as rc]))

(defn typeahead-results [typed-text]
  "Normally we'd pass the typed-text to a backend service, here we
  just use the sample data from the local variable
  all-typeahead-values"
  (filterv
   #(re-find (re-pattern (.toLowerCase typed-text))
             (.toLowerCase %))
   ["wood" "metal" "concrete" "glass" "cloth" "rubber"]))

(defn on-js-reload []
  (reagent/render
   [rc/type-ahead {:typeahead-fn typeahead-results
                   :placeholder "enter a materiallo..."}]
   (.getElementById js/document "app")))

(defn ^:export main []
  (on-js-reload))

(on-js-reload)

;; ---------testing

(comment
  (type-ahead-options "i"))
