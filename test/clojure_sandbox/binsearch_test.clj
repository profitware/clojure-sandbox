(ns clojure-sandbox.binsearch-test
  (:require [clojure.test :refer :all]
            [clojure-sandbox.binsearch :as bs]))

(deftest midpoint-test
  (testing "equal"
           (are [x y] (= x y)
                0 (bs/midpoint 0 0)
                1 (bs/midpoint 1 1)))

  (testing "neighbors"
           (are [x y] (= x y)
                0 (bs/midpoint 0 1)
                1 (bs/midpoint 1 2)
                2 (bs/midpoint 1 4)))
  
  (testing "middle"
           (are [x y] (= x y)
                1 (bs/midpoint 0 2)
                3 (bs/midpoint 1 5))))

(deftest binsearch-test
  (testing "not found"
           (are [x] (nil? x)
                (bs/binsearch [] 1)
                (bs/binsearch [1 2 3] 4)
                (bs/binsearch [-1 2 4] 0)))

  (testing "binsearch"
           (are [x y] (= x y)
                2 (bs/binsearch [1 3 4 6 8 9 11] 4)
                3 (bs/binsearch [1 2 3 4 6 8 9 11] 4)
                0 (bs/binsearch [1 2 3 4 6 8 9 11] 1)
                7 (bs/binsearch [1 2 3 4 6 8 9 11] 11)
                6 (bs/binsearch [1 2 3 4 8 9 11] 11))))
