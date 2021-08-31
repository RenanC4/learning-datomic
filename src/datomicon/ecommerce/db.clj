(ns datomicon.ecommerce.db
  (:require [datomic.api :as d]))

(def db-uri "datomic:dev://localhost:4334/ecommerce")

(defn connection
  []
  (d/create-database db-uri)
  (d/connect db-uri))

(def schema [{:db/ident :produto/name
              :db/valueType :db.type/string
              :db/cardinality :db.cardinality/one}
              {:db/ident :produto/slug
              :db/valueType :db.type/string
              :db/cardinality :db.cardinality/one}
              {:db/ident :produto/price
              :db/valueType :db.type/bigdec
              :db/cardinality :db.cardinality/one}
              {:db/ident :produto/palavras-chave
               :db/valueType :db.type/string
               :db/cardinality :db.cardinality/many}])

(defn cria-schema [conn]
  (d/transact conn schema))
