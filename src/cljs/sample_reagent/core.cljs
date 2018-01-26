(ns sample-reagent.core
  (:require
   [re-com.core :as recom]
   [reagent.core :as reagent]))

(defn some-component [ratom]
  [:div (:text @ratom)])

(defn some1 []
  [:div "abc"])

(defn my-button []
  [recom/h-box
   :gap "10px"
   :justify :around
   :children
   [[recom/button
     :class "btn-error"
     :tooltip "hello there "
     :label "def"]
    [recom/button
     :class "btn-error"
     :tooltip "hello there "
     :label "abc"]]])

;; (reagent/render-to-static-markup some1)

(defonce app-state
  (reagent/atom {:text "Hello, what is your name??"}))

(defn on-js-reload []
  (reagent/render [my-button]
            (.getElementById js/document "app")))

(defn ^:export main []
  (on-js-reload))

(on-js-reload)

(comment
  (swap! app-state assoc :text "a new string..."))
