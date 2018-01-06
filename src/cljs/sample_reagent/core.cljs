(ns sample-reagent.core
  (:require
   [reagent.core :as reagent]))

(defn some-component [ratom]
  [:div (:text @ratom)])

(defonce app-state
  (reagent/atom {:text "Hello, what is your name??"}))

(defn on-js-reload []
  (reagent/render [some-component app-state]
            (.getElementById js/document "app")))

(defn ^:export main []
  (on-js-reload))

(on-js-reload)

(comment
  (swap! app-state assoc :text "a new string..."))
