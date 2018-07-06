(ns sample-reagent.core
  (:require
   [reagent.core :as reagent]))

(def person (reagent/atom "Rod"))

(defn get-name []
  (->> "name-box"
       (.getElementById js/document)
       .-value))

(defn change-name-button []
  [:button
   {:on-click #(reset! person (get-name))}
   "Change the Name!"])

(defn input-box []
  [:input {:id "name-box"}])

(defn body []
  [:div
   [input-box]
   [:br]
   @person
   [:br]
   [change-name-button]])

(defn on-js-reload []
  (reagent/render
   [body]
   (.getElementById js/document "app")))

(defn ^:export main []
  (on-js-reload))

(on-js-reload)






