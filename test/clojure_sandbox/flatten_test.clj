(ns clojure-sandbox.flatten-test
  (:require [clojure.test :refer :all]
            [clojure-sandbox.flatten :as f]))

(deftest flatten-test
  (testing "empty list"
           (is (= (list) (f/flatten-list))))
  
  (testing "one element list"
           (is (= '(1) (f/flatten-list 1))))

  (testing "three elements list"
           (are [x y] (= x y)
                '(1 2 3) (f/flatten-list 1 2 3)
                '(1 2 3) (f/flatten-list '(1) 2 3)
                '(1 2 3) (f/flatten-list '(1) '(2 3))
                '(1 2 3) (f/flatten-list '(1 2 3))
                '(1 2 3) (f/flatten-list '(1 (2 3)))
                '(1 2 3) (f/flatten-list '((1 2 3))))))
