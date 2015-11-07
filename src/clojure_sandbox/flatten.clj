(ns clojure-sandbox.flatten
  (:gen-class))

(defn flatten-list
  ([]
    (list))
  ([first-element & rest-list]
    (println first-element "and" rest-list)
    (if (empty? rest-list)
      (list first-element)
      (if (list? first-element)
        (concat first-element (apply flatten-list rest-list))
        (cons first-element (apply flatten-list rest-list))))))
