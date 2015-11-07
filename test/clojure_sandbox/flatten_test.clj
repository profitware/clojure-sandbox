(ns clojure-sandbox.flatten-test
  (:require [clojure.test :refer :all]
            [clojure-sandbox.flatten :as f]))

(deftest flatten-test
  (testing "empty list"
    (is (= (list) (f/flatten-list))))
  
  (testing "one element list"
    (is (= '(1) (f/flatten-list 1))))

  (testing "three elements test0"
    (is (= '(1 2 3) (f/flatten-list 1 2 3))))
  
  (testing "three elements test1"
    (is (= '(1 2 3) (f/flatten-list '(1) 2 3))))

  (testing "three elements test2"
    (is (= '(1 2 3) (f/flatten-list '(1) '(2 3)))))

  (testing "three elements test3"
    (is (= '(1 2 3) (f/flatten-list '(1 2 3)))))

  (testing "three elements test4"
    (is (= '(1 2 3) (f/flatten-list '(1 (2 3))))))

  (testing "three elements test5"
    (is (= '(1 2 3) (f/flatten-list '((1 2 3)))))))
