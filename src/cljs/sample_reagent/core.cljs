(ns sample-reagent.core
  (:require
   [reagent.core :as reagent]
   ;; [rum.core :as rum]
   ))

;; (rum/defc label [text]
;;   [:div {:class "label"} text])

(def person (reagent/atom "Rod"))

(comment
  (reset! person "blatttth"))


(defn get-name []
  (->> "name-box"
       (.getElementById js/document)
       .-value))

(defn change-name-button []
  [:button
   {:on-click #(reset! person (get-name))}
   "Change Name!"])

(defn input-box []
  [:input {:id "name-box"}])

(defn body []
  [:div
   [input-box]
   [:br]
   @person
   [:br]
   [change-name-button]])

;; (defn mount-rum []
;;   (rum/mount (label "abc123") js/document.body))

(defn mount-reagent []
  (reagent/render
   [body]
   (.getElementById js/document "app")))

(defn on-js-reload []
  (reagent/render
   [body]
   (.getElementById js/document "app")))

(defn ^:export main []
  (on-js-reload))

(on-js-reload)






