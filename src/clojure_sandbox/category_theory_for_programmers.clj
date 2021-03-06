(ns clojure-sandbox.category-theory-for-programmers
  (:use [clojure.test :as test]))


;; Chapter 1

(defn my-identity
  "Identity function"
  [x]
  x)


(defn my-comp
  "Composition function"
  [f g]
  (fn [& args]
    (f (apply g args))))


(deftest my-comp-and-identity
  "Tests if identity works fine with function composition"
  (let [multiplyby2 #(* % 2)
        function1 (my-comp my-identity inc)
        function2 (my-comp inc my-identity)
        function3 (my-comp my-identity (my-comp multiplyby2 inc))
        function4 (my-comp (my-comp my-identity multiplyby2) inc)]
    (is (= (function1 1) (function2 1)))
    (is (= (function3 1) (function4 1)))))


;; Chapter 2

(defn my-memoize
  "Memoization function"
  [f]
  (let [value (atom {})]
    (fn [& args]
      (get (swap! value
                  assoc
                  [f args]
                  (if (contains? @value [f args])
                    (get @value [f args])
                    (apply f args)))
           [f args]))))


(def my-random (my-memoize rand))


(def my-factorial (my-memoize #(apply * (range 1 (inc %)))))


(def my-println (my-memoize #(println "Hello")))


;; Chapter 3

(defprotocol MonoidProto
  (mappend [monoid m n])
  (mempty [monoid]))


(defrecord Monoid [mappend-func mempty-value]
  MonoidProto
  (mappend [monoid m n] (apply mappend-func [m n]))
  (mempty [monoid] mempty-value))


(def numeric-monoid (->Monoid + 0))
(def string-monoid (->Monoid str ""))
(def bool-true-monoid (->Monoid #(and %1 %2) true))
(def bool-false-monoid (->Monoid #(or %1 %2) false))
(def numeric-add-modulo-monoid-factory (fn [divisor]
                                         (->Monoid (comp #(mod % divisor) +)
                                                   divisor)))
(def numeric-add-modulo3-monoid (numeric-add-modulo-monoid-factory 3))


;; Chapter 4

(defprotocol KleisliProto
  (kidentity [kleisli x])
  (kcompose [kleisli f g]))


(defrecord WriterMonad [writer-monoid]
  KleisliProto
  (kidentity [kleisli x] [x (.mempty writer-monoid)])
  (kcompose [kleisli f g] (fn [x]
                            (let [[r1 w1] (g x)
                                  [r2 w2] (f r1)]
                              [r2 (.mappend writer-monoid w1 w2)]))))


(def string-writer (->WriterMonad string-monoid))


(defn >=> [& args]
  (reduce (fn [acc kfunction]
            (.kcompose string-writer kfunction acc))
          #(.kidentity string-writer %)
          args))


(defn k-up-case [x]
  [(.toUpperCase x) "k-up-case "])


(defn k-words [x]
  [(clojure.string/split x #" ") "k-words "])


(defn k-process [x]
  (apply (>=> k-up-case k-words)
         [x]))


(defprotocol OptionalProto
  (is-valid [optional])
  (value [optional]))


(defrecord Optional [value is-valid]
  OptionalProto
  (is-valid [optional] is-valid)
  (value [optional] value))


(defrecord OptionalMonad []
  KleisliProto
  (kidentity [kleisli x] (->Optional x true))
  (kcompose [kleisli f g] (fn [x]
                            (let [o1 (g x)]
                              (if (.is-valid o1)
                                (f (.value o1))
                                (->Optional nil false))))))


(def optional (->OptionalMonad))
(def optional-non-valid (->Optional nil false))


(defn safe-root [x]
  (if (> x 0)
    (.kidentity optional (Math/sqrt x))
    optional-non-valid))


(defn safe-reciprocal [x]
  (if (= x 0)
    optional-non-valid
    (.kidentity optional (/ 1 x))))


(defn safe-root-reciprocal [x]
  (apply (.kcompose optional safe-root safe-reciprocal) [x]))


(deftest safe-root-reciprocal-test
  (is (= optional-non-valid (safe-root-reciprocal -1)))
  (is (= optional-non-valid (safe-root-reciprocal 0)))
  (is (= 1.0 (.value (safe-root-reciprocal 1)))))
