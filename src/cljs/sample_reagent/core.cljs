(ns sample-reagent.core
  (:require
   [reagent.core :as reagent]))

(defn render-up [state]
  (if (not state)
    [:div {:class-name "up-down-option-none"}]
    [:div {:class-name "up-down-option"} (:text state)]))

(def render-down render-up)

(defn render-left [state]
  (if (not state)
    [:div {:class-name "left-option-none"}]
    [:div {:class-name "left-option"} (:text state)]))

(defn render-right [state]
  (if (not state)
    [:div {:class-name "right-option-none"}]
    [:div {:class-name "right-option"} (:text state)]))

(defn render-text [text] [:p text])

(defn render-scene [state]
  [:div
   [render-up (-> @state :first-message :up)]
   [render-left (-> @state :first-message :left)]
   [render-right (-> @state :first-message :right)]

   [:div {:class-name "card"}
    [:div {:class-name "text"}

     (map (fn [txt] [:p txt]) (-> @state :first-message :text))
     ]]

   [render-down (-> @state :first-message :down)]])

;; (reagent/render-to-static-markup some1)

(defonce app-state
  (reagent/atom
   {:first-message
    {:text ["Serenity:"
            "Communications repaired."
            "Stable. In stasus"
            "Simone: Stable. In stasus."
            "Will: No heartbeat."
            "Shiobhan: Stable, In stasus."]
     :right {:text "Command: Reanimate crew."
             :next :adrenaline-injected}}}))

(defn on-js-reload []
  (reagent/render [render-scene app-state]
            (.getElementById js/document "app")))

(defn ^:export main []
  (on-js-reload))

(on-js-reload)

(comment
  (swap! app-state assoc :text "a new string..."))
