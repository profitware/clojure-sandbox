(ns clojure-sandbox.binsearch
  (:gen-class))

(defn midpoint
  [imin imax]
  (int (/ (+ imin imax) 2)))

(defn binsearch  
  ([[& binsearch-vec] item]
    (let [vec-len (count binsearch-vec)]
      (if 
        (= 0 vec-len)
            nil
        vec-len))))
