(ns clojure-sandbox.binsearch
  (:gen-class))

(defn midpoint
  [imin imax]
  (int (/ (+ imin imax) 2)))

(defn binsearch  
  ([[& binsearch-vec] item]
    (let [vec-len (count binsearch-vec)
          last-vec-index (dec vec-len)]
      (cond
        (= 0 vec-len) nil
        (= item (nth binsearch-vec last-vec-index)) last-vec-index
        :else (do
                (defn binsearch-inner
                  [imin imax previous-index]
                  (let [current-index (midpoint imin imax)
                        current-value (nth binsearch-vec current-index)]
                    (cond
                      (= previous-index current-index) nil
                      (= item current-value) current-index
                      (< item current-value) (binsearch-inner imin current-index current-index)
                      :else (binsearch-inner current-index imax current-index))))
                (binsearch-inner 0 last-vec-index -1))))))
