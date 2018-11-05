(ns sample-reagent.data
  (:require
   [datascript.core :as d]
   [reagent.core :as reagent]))

(def schema {:cars {:db/type :db.type/ref
                    :db/cardinality :db.cardinality/many}}) 

(def conn (d/create-conn schema))
(d/transact! conn [{:db/id -2
                    :car/name "honda civic"}
                   {:db/id -3
                    :car/name "honda prelude"}
                   {:db/id -4
                    :car/name "toyota tacoma"}
                   {:db/id -5
                    :car/name "bmw 325xi"}
                   {:db/id -2
                    :user/name "Peter"
                    :cars [-2 -3]}
                   {:db/id -1
                    :user/name "Fenton"
                    :cars [-4 -5]}]) 

(defn qry []
  (d/q
   '[:find (pull ?e [:user/name {:cars [:car/name]}]) 
     :where
     [?e :user/name]]
   @conn the-qry))

(d/q '[:find (pull ?e [:db/id]) 
       :where
       [?e]]
     @conn)

(d/transact! conn [{:db/id 1
                    :maker/name "BMW"}])
(d/transact! conn [{:db/id 1
                    :maker/name "BMW"}])


(def d1 [{:db/id 2 :car/maker {:db/id 1 :maker/name "Honda"}}
         {:db/id 3 :car/maker {:db/id 1 :maker/name "Toyota"}}])






