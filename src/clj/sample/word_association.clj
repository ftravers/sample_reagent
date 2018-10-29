(ns sample.word-association
  (:require [clojure.string :as string]))

(defn game [] (string/split-lines (slurp "words.txt")))

 (with-open [rdr (clojure.java.io/reader "words.txt")]
   (count (line-seq rdr)))

