(ns sample-reagent.core
  (:require
   [reagent.core :as reagent]))

(def all-typeahead-values
  ["wood" "metal" "concrete" "glass" "cloth"])

(declare get-typeahead-sublist type-ahead-options
         data-list input-elem)

(defn body []
  (let [input-value (reagent/atom "")]
    (fn []
      [:div
       [input-elem input-value]
       [data-list input-value]])))

(defn input-elem [input-value]
  [:input {:type "text"
           :list "json-datalist"
           :placeholder "enter a material..."
           :value @input-value
           :on-change #(reset! input-value (-> % .-target .-value))}])

(defn data-list []
  (fn [input-value]
    [:datalist {:id "json-datalist"}
     (type-ahead-options all-typeahead-values @input-value)]))

(defn type-ahead-options [all-options typed-text]
  (for [option
        (get-typeahead-sublist all-options typed-text)]
    ^{:key option} [:option {:value option}]))

(defn get-typeahead-sublist [full-list typed-text]
  (filterv #(re-find (re-pattern (.toLowerCase typed-text))
                     (.toLowerCase %))
           full-list))

(defn on-js-reload []
  (reagent/render
   [body]
   (.getElementById js/document "app")))

(defn ^:export main []
  (on-js-reload))

(on-js-reload)






