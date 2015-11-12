(ns clojure-sandbox.flatten
  (:require [clojure.walk :as w])
  (:gen-class))

(defn flatten-list 
  ([]
    (list))
  
  ([& args]
    (def accumulator (list))
    (defn flatten-list-inner
      ([args-inner]
        (if (seq? args-inner)
          (w/walk flatten-list-inner concat args-inner)
          (def accumulator (cons args-inner accumulator)))))
    (flatten-list-inner args)
    (reverse accumulator)))
