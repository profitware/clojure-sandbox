(ns clojure-sandbox.core
  (:require [clojure-sandbox.binsearch :as bs])
  (:gen-class))

(defn -main
  []
  (println "Middle point for 1 and 2 is" (bs/midpoint 1 2))
  (println "Binsearch [1 2 3 4 6 8 9 11] 4 is" (bs/binsearch [1 2 3 4 6 8 9 11] 4)))
