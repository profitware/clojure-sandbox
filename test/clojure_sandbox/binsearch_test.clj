(ns clojure-sandbox.binsearch-test
  (:require [clojure.test :refer :all]
            [clojure-sandbox.binsearch :refer :all]))

(deftest midpoint-test
  (testing "zero zero"
    (is (= 0 (midpoint 0 0))))

  (testing "zero one"
    (is (= 0 (midpoint 0 1))))

  (testing "zero two"
    (is (= 1 (midpoint 0 2))))

  (testing "one one"
    (is (= 1 (midpoint 1 1))))

  (testing "one two"
    (is (= 1 (midpoint 1 2))))

  (testing "one five"
    (is (= 3 (midpoint 1 5)))))
