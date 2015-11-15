(ns clojure-sandbox.flatten
  (:require [clojure.walk :as w])
  (:gen-class))

(defn flatten-list 
  ([]
    (list))
  
  ([& args]
    (def accumulator (atom []))
    (defn flatten-list-inner
      ([args-inner]
        (if (seq? args-inner)
          (w/walk flatten-list-inner concat args-inner)
          (swap! accumulator conj args-inner))))
    (flatten-list-inner args)
    (let [[& out-list] @accumulator]
      out-list)))
