(ns cljs.sample-reagent.core-test
  (:require [cljs.test :refer-macros [deftest is testing run-tests]]
            [sample-reagent.core :as sut]))

(deftest test-numbers
  (is (= 1 1)))

(cljs.test/run-tests)
