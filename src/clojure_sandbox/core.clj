(ns clojure-sandbox.core
  (:require [clojure-sandbox.binsearch :as bs])
  (:gen-class))

(defn -main
  []
  (println "Middle point for 1 and 2 is" (bs/midpoint 1 2))
  (println "Binsearch [1 2] 1 is" (bs/binsearch [1 2] 1)))
