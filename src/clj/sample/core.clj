(ns sample.core
  (:require [datomic.api :as datomic]))

(def schema
  [{:db/doc "A users email."
    :db/ident :user/email
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one
    :db.install/_attribute :db.part/db}
   {:db/doc "age"
    :db/ident :user/age
    :db/valueType :db.type/long
    :db/cardinality :db.cardinality/one
    :db.install/_attribute :db.part/db}])

(def db-url "datomic:free://127.0.0.1:4334/omn-dev")

(def test-data
  [{:user/email "fred.jones@gmail.com"
    :user/age 23}
   {:user/email "abc@gmail.com"
    :user/age 2}
   {:user/email "alice@hotmail.com"
    :user/age 233}])

(defn transact-data [data]
  (-> db-url
      datomic/connect
      (datomic/transact data)))

(defn db [] (-> db-url datomic/connect datomic/db))

(defn query1 [db]
  (datomic/q '[:find (pull ?e [:db/id :user/email :user/age])
               :where
               [?e :user/email]
               [?e :user/age]]
             db))
