(ns sample-reagent.core
  (:require
   [reagent.core :as reagent]))

(def all-typeahead-values
  ["wood" "metal" "concrete" "glass" "cloth"])

(defn get-typeahead-sublist [full-list typed-text]
  (filterv
   (fn [curr-elm]
     (re-find (re-pattern (.toLowerCase typed-text))
              (.toLowerCase curr-elm)))
   full-list))

(defn type-ahead-options [all-options typed-text]
  (let [sub-options
        (get-typeahead-sublist all-options typed-text)]
    (for [option sub-options]
      ^{:key option}
      [:option {:value option}])))

(defn body []
  (let [input-value (reagent/atom "")]
    (fn []
      [:div
       [:input
        {:type "text"
         :list "json-datalist"
         :placeholder "enter a material..."
         :value @input-value
         :on-change #(reset! input-value (-> % .-target .-value))}]
       [:datalist {:id "json-datalist"}
        (type-ahead-options all-typeahead-values @input-value)]])))

(defn on-js-reload []
  (reagent/render
   [body]
   (.getElementById js/document "app")))

(defn ^:export main []
  (on-js-reload))

(on-js-reload)






