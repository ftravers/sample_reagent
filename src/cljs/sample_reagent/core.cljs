(ns sample-reagent.core
  (:require
   [sample-reagent.highlight-letters :as hl]
   [reagent.core :as reagent]))

(def all-typeahead-values
  ["wood" "metal" "concrete" "glass" "cloth" "rubber"]) 

(declare get-typeahead-sublist type-ahead-options
         data-list input-elem)

(defn type-ahead [typeahead-results-fn]
  (let [input-value (reagent/atom "")]
    (fn []
      [:div
       [input-elem input-value]
       [data-list input-value typeahead-results-fn]])))

(defn input-elem [input-value]
  [:input {:type "text"
           :list "json-datalist"
           :placeholder "enter a material..."
           :value @input-value
           :on-change #(reset! input-value (-> % .-target .-value))}])

(defn data-list [input-value-atm get-typeahead-sublist-fn]
  [:datalist {:id "json-datalist"}
   (type-ahead-options @input-value-atm get-typeahead-sublist-fn)])

(defn type-ahead-options [typed-text get-typeahead-sublist-fn]
  (for [option
        (get-typeahead-sublist-fn typed-text)]
    ^{:key option} [:option {:value option}]))

(defn typeahead-results [typed-text]
  "Normally we'd pass the typed-text to a backend service, here we
  just use the sample data from the local variable
  all-typeahead-values"
  (filterv #(re-find (re-pattern (.toLowerCase typed-text))
                     (.toLowerCase %))
           all-typeahead-values))

(defn on-js-reload []
  (reagent/render
   [type-ahead typeahead-results]
   (.getElementById js/document "app")))

(defn ^:export main []
  (on-js-reload))

(on-js-reload)

;; ---------testing

(comment
  (type-ahead-options "i"))
