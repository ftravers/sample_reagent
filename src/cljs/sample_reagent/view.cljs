(ns sample-reagent.view
  (:require
   [reagent.core :as reagent]
   [sample-reagent.data :as data]))

(defn cars [the-cars]
  [:ul (map (fn [acar] [:li (:car/name acar)]) the-cars)])

(defn big-query []
  (data/q2 [:user/name {:cars [:car/name]}]))

(defn users [the-data]
  [:ul
   (map
    (fn [user]
      [:li (-> user first :user/name)
       [cars (-> user first :cars)]])
    the-data)])

(defn on-js-reload []
  (reagent/render
   [users (big-query)]
   (.getElementById js/document "app")))
(defn ^:export main []
  (on-js-reload))
(on-js-reload)
