(ns clojure-sandbox.core
  (:require [clojure-sandbox.binsearch :as bs])
  (:gen-class))

(defn -main
  []
  (println "Middle point for 1 and 2 is" (bs/midpoint 1 2)))
