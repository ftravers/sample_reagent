(ns sample-reagent.reusable-components
  (:require
   [reagent.core :as reagent]))

(declare type-ahead-options input-elem data-list)

(defn type-ahead
  "Type Ahead Component.
  ARGS:
  :typeahead-results-fn
  Is a function that takes one argument, the current search text in
  the typeahead box.  It returns a vector of strings that are the
  matches for the passed in search text.

  :placeholder
  placeholder text for text input element  
  "
  [{:keys [typeahead-fn placeholder] }]
  (let [input-value (reagent/atom "")]
    (fn []
      [:div
       [input-elem input-value placeholder]
       [data-list input-value typeahead-fn]])))

(defn input-elem [input-value placeholder]
  [:input {:type "text"
           :list "json-datalist"
           :placeholder placeholder
           :value @input-value
           :on-change #(reset! input-value (-> % .-target .-value))}])

(defn data-list [input-value-atm get-typeahead-sublist-fn]
  [:datalist {:id "json-datalist"}
   (type-ahead-options @input-value-atm get-typeahead-sublist-fn)])

(defn type-ahead-options [typed-text get-typeahead-sublist-fn]
  (for [option
        (get-typeahead-sublist-fn typed-text)]
    ^{:key option} [:option {:value option}]))
