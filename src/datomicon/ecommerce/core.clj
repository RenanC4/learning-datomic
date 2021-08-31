(ns datomicon.ecommerce.core
  (:require [datomicon.ecommerce.db :as db]
            [datomicon.ecommerce.model :as m]
            [datomic.api :as d]))

(def conn (db/connection))

(db/cria-schema conn)

(let [computador (m/new-product "Computador2", "/computador_novo", 2500.10M, "batata")]
  (d/transact conn [computador]))

(let [mouse (m/new-product "Mouse", "/mouse_novo", 25.50M)]
  (d/transact conn [mouse]))

(let [calculadora {:produto/name "Calculadora"}]
  (d/transact conn [calculadora]))

(def dbbolado (d/db conn))

(d/q '[:find ?e
       :where [?e :produto/name "Calculadora"]] dbbolado)


(defn find-generico
  [dbbolado campo-a-ser-buscado]
  (d/q '[:find ?e
         :in $ ?campo-a-ser-buscado
         :where [?e :produto/slug ?campo-a-ser-buscado]]
       dbbolado
       campo-a-ser-buscado))

(find-generico dbbolado "/mouse_novo")

(find-generico dbbolado "/computador_novo")

(d/q '[:find ?price ?name
       :keys  preco nome
       :where [?e :produto/price ?price]
              [?e :produto/name ?name]
              [(> ?price 100)]] dbbolado)


(d/q '[:find (pull ?e [:produto/name :produto/price :produto/slug :produto/palavras-chave])
       :where [?e :produto/price ?price]
              [(> ?price 100)]] dbbolado)

(d/q '[:find (pull ?e [*])
       :where [?e :produto/name ?name]] dbbolado)
