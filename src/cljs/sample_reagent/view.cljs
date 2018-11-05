(ns sample-reagent.view
  (:require
   [reagent.core :as reagent]
   [sample-reagent.data :as data]))

;; (mapv #(-> % first :user/name) res)

;; [#:car{:name "toyota tacoma"} #:car{:name "bmw 325xi"}]
(def d1
  [{:user/name "Peter",
    :cars [#:car {:name "honda civic"} #:car {:name "honda prelude"}]}
   {:user/name "Fenton",
    :cars [#:car {:name "toyota tacoma"} #:car {:name "bmw 325xi"}]}])

(defn cars [the-cars]
  [:ul (map (fn [acar] [:li (:car/name acar)]) the-cars)])

(defn users [the-data]
  [:ul
   (map (fn [user]
          [:li (:user/name user)
            [cars (:cars user)]
           ])
        the-data)])

(defn on-js-reload []
  (reagent/render
   ;; [cars (-> d1 first :cars)]
   [users d1]
   (.getElementById js/document "app")))
(defn ^:export main []
  (on-js-reload))
(on-js-reload)

