(ns clojure-sandbox.binsearch
  (:gen-class))

(defn midpoint
  [imin imax]
  (int (/ (+ imin imax) 2)))
