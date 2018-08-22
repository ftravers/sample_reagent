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
(assert (= ["abc"] 
           (get-typeahead-sublist ["abc" "def"] "a")))
(assert (= ["def"] 
           (get-typeahead-sublist ["abc" "def"] "e")))

(defn handle-type-ahead-on-change
  [all-typeahead-values-list value-ratom]
  (fn [event] 
    (let [element (-> event .-target)
          input-elm-val (-> element .-value)]
      
      (reset! value-ratom input-elm-val))))

(defn type-ahead-options [all-options typed-text]
  (let [sub-options
        (get-typeahead-sublist all-options typed-text)]
    (for [option sub-options]
      ^{:key option}
      [:option {:value option}])))

(defn body []
  (let [visible-options (reagent/atom [])
        input-value (reagent/atom "")]
    (fn []
      [:div
       [:input
        ;; @typed-text
        {:type "text"
         :id "ajax"
         :list "json-datalist"
         :placeholder "e.g. datalist"
         :value @input-value
         :on-change
         (handle-type-ahead-on-change
          all-typeahead-values
          input-value)
         }]
       [:datalist {:id "json-datalist"}
        (type-ahead-options all-typeahead-values @input-value)]])))

(defn on-js-reload []
  (reagent/render
   [body]
   (.getElementById js/document "app")))

(defn ^:export main []
  (on-js-reload))

(on-js-reload)






