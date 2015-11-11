(ns clojure-sandbox.flatten
  (:require [clojure.walk :as w])
  (:gen-class))

(defn flatten-list [args]
  (def accumulator (list))
  (defn flatten-list-inner
    ([args-inner]
      (if (list? args-inner)
        (do
          (def x (w/walk flatten-list-inner concat args-inner))
          x)
        (do
          (def accumulator (cons args-inner accumulator))
          args-inner))))
  (flatten-list-inner args)
  (reverse accumulator))
