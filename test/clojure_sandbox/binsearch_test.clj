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

(deftest binsearch-test
  (testing "not found test1"
    (nil? (binsearch []  1)))
  
  (testing "binsearch test1"
    (is (= 2 (binsearch [1 3 4 6 8 9 11] 4))))
  
  (testing "binsearch test2"
    (is (= 3 (binsearch [1 2 3 4 6 8 9 11] 4))))

  (testing "binsearch test3"
    (is (= 0 (binsearch [1 2 3 4 6 8 9 11] 1))))

  (testing "binsearch test4"
    (is (= 7 (binsearch [1 2 3 4 6 8 9 11] 11))))

  (testing "binsearch test5"
    (is (= 6 (binsearch [1 2 3 4 8 9 11] 11))))
  
  (testing "not found test2"
    (nil? (binsearch [1 2 3] 4)))
  
  (testing "not found test3"
    (nil? (binsearch [-1 2 4] 0))))
