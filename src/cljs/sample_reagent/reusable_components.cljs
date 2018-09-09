(ns sample-reagent.reusable-components
  (:require
   [reagent.core :as reagent]))

;; ============ Typeahead Component =================

(comment
  "TODO:
Setup some kind of function to run when a value is selected
Which event is that I wonder?  on-select?

")

(declare type-ahead-options input-elem data-list)

(defn type-ahead
  "type ahead component.
  args:
  :typeahead-results-fn
  is a function that takes one argument, the current search text in
  the typeahead box.  it returns a vector of strings that are the
  matches for the passed in search text.

  :placeholder
  placeholder text for text input element  
  "
  [{:keys [typeahead-fn placeholder] }]
  (let [input-value (reagent/atom "")
        datalist-id (random-uuid)]
    (.log js/console datalist-id)
    (fn []
      [:div
       [input-elem input-value placeholder datalist-id]
       [data-list input-value typeahead-fn datalist-id]])))

(defn input-elem [input-value placeholder datalist-id]
  (fn [input-value]
    [:input {:type "text"
             :list datalist-id
             :placeholder placeholder
             :value @input-value
             :on-change #(reset! input-value (-> % .-target .-value))}]))

(defn data-list [input-value-atm get-typeahead-sublist-fn datalist-id]
  (fn [input-value-atm]
    [:datalist {:id datalist-id}
     (type-ahead-options @input-value-atm get-typeahead-sublist-fn)]))

(defn type-ahead-options [typed-text get-typeahead-sublist-fn]
  (for [option
        (get-typeahead-sublist-fn typed-text)]
    ^{:key option} [:option {:value option}]))
