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

(defn render-scene [state messages]
  (reagent/create-class
    {:component-did-mount
     #(js/key "right" (fn []
                        (let [next (get messages (get-in @state [:right :next]))]
                          (.log js/console (str "Setting right to " next))
                          (if (not (nil? next))
                              (reset! state next)))))
     :component-will-unmount #(.unbind js/key ("right"))
                         :reagent-render (fn []
    [:div
   [render-up (-> @state :up)]
   [render-left (-> @state :left)]
   [render-right (-> @state :right)]

   [:div {:class-name "card"}
    [:div {:class-name "text"}

     (map (fn [txt] [:p txt]) (-> @state :text))
     ]]

   [render-down (-> @state :down)]])
                         }))

;; (reagent/render-to-static-markup some1)

(defonce app-messages {:first-message {:text ["Serenity:"
                                              "Communications repaired."
                                              "Stable. In stasus"
                                              "Simone: Stable. In stasus."
                                              "Will: No heartbeat."
                                              "Shiobhan: Stable, In stasus."]
                                       :right {:text "Command: Reanimate crew."
                                               :next :adrenaline-injected}}
                       :adrenaline-injected {:text ["Command has been sent."
                                                    "Waiting for response."]
                                             :right {:text "Wait."
                                                     :next :day-1.5
                                                     :message "wait"}}
                       :day-1.5 {:text ["A terminal light blinks."
                                        "A distress signal."]
                                 :right {:text "Read distress."}
                                 :down {:text "Terminal."}}})


(defonce app-state
  (reagent/atom (:first-message app-messages)))

(defn on-js-reload []
  (reagent/render [render-scene app-state app-messages]
            (.getElementById js/document "app")))

(defn ^:export main []
  (on-js-reload))

(on-js-reload)

(comment
  (swap! app-state assoc :text "a new string..."))
