(ns sample-reagent.core-test
  (:require [cljs.test :refer-macros [deftest is testing run-tests]]
            [cljs-test-display.core]
            [sample-reagent.core :as sut]))

(deftest test-numbers
  (is (= 1 1)))

(cljs.test/run-tests
 (cljs-test-display.core/init! "app"))
