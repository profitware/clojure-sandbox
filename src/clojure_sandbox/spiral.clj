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
        reduced-dict (reduce (fn [acc value]
                               (let [{x :x
                                      y :y
                                      turn :turn
                                      steps-till-turn :steps-till-turn} acc
                                     [x-inc y-inc] (get turn-dict
                                                        (mod turn 4))
                                     steps-next-turn (get steps-till-turn-list turn)
                                     new-steps-till-turn (let [x (dec steps-till-turn)]
                                                           (if (< x 1)
                                                             steps-next-turn
                                                             x))]
                                 (assoc acc
                                        [x y] value
                                        :x (+ x x-inc)
                                        :y (+ y y-inc)
                                        :steps-till-turn new-steps-till-turn
                                        :turn (if (and new-steps-till-turn
                                                       (> new-steps-till-turn 1))
                                                turn
                                                (inc turn)))))
                             {:x 0
                              :y 0
                              :turn 0
                              :steps-till-turn num}
                             (range (* num num) 0 -1))]
    (loop [y 0
           full-string ""]
      (let [line (loop [x 0
                        string ""]
                   (if (< x num)
                     (recur (inc x)
                            (str string
                                 (get reduced-dict [x y])
                                 (when (< (inc x) num)
                                   " ")))
                     string))]
        (if (< y num)
          (recur (inc y)
                 (str full-string line "\n"))
          full-string)))))
