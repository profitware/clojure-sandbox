(ns clojure-sandbox.spiral)


(defn spiral [num]
  (let [turn-dict {0 [1 0]
                   1 [0 1]
                   2 [-1 0]
                   3 [0 -1]}
        steps-till-turn-list (into []
                                   (drop 1
                                         (apply concat
                                                (for [n (range num 0 -1)]
                                                  [n n]))))
        index-dict (loop [dict {}
                          x 0
                          y 0
                          value (* num num)
                          turn 0
                          steps-till-turn num]
                     (let [new-dict (assoc dict [x y] value)
                           [x-inc y-inc] (get turn-dict
                                              (mod turn 4))
                           new-x (+ x x-inc)
                           new-y (+ y y-inc)
                           new-value (dec value)
                           steps-next-turn (get steps-till-turn-list turn)
                           new-steps-till-turn (let [x (dec steps-till-turn)]
                                                 (if (< x 1)
                                                   steps-next-turn
                                                   x))
                           new-turn (if (and new-steps-till-turn
                                             (> new-steps-till-turn 1))
                                      turn
                                      (inc turn))]
                       (if new-steps-till-turn
                         (recur new-dict
                                new-x
                                new-y
                                new-value
                                new-turn
                                new-steps-till-turn)
                         new-dict)))]
    (loop [y 0
           full-string ""]
      (let [line (loop [x 0
                        string ""]
                   (if (< x num)
                     (recur (inc x)
                            (str string
                                 (get index-dict [x y])
                                 (when (< (inc x) num)
                                   " ")))
                     string))]
        (if (< y num)
          (recur (inc y)
                 (str full-string line "\n"))
          full-string)))))
