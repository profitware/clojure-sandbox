(ns clojure-sandbox.core
  (:require [clojure-sandbox.binsearch :as bs]
            [clojure-sandbox.flatten :as f]
            [clojure-sandbox.spiral :as s])
  (:gen-class))

(defn -main
  []
  (println "Middle point for 1 and 2 is" (bs/midpoint 1 2))
  (println "Binsearch [1 2 3 4 6 8 9 11] 4 is" (bs/binsearch [1 2 3 4 6 8 9 11] 4))
  (println "Flatten (((1 (2)) 3)) (4) is" (f/flatten-list '(((1 (2)) 3)) '(4)))
  (println "Spiral for 5 is")
  (println (s/spiral 5)))
