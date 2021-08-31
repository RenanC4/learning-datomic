(ns datomicon.ecommerce.model)

(defn new-product [name slug price keyword]
  {:produto/name name
   :produto/slug slug
   :produto/price price
   :produto/palavras-chave keyword})

