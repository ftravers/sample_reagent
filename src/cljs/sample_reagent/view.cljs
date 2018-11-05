(ns sample-reagent.view
  (:require
   [reagent.core :as reagent]
   [sample-reagent.data :as data]))

;; (mapv #(-> % first :user/name) res)

(defn some-component []
  (let [users (mapv #(-> % first :user/name) (data/qry))]
    [:ul
     (map (fn [x] ^{:key x} [:li x]) users)]))

(defn on-js-reload []
  (reagent/render [some-component]
            (.getElementById js/document "app")))
(defn ^:export main []
  (on-js-reload))
(on-js-reload)
