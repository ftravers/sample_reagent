(ns sample.poker
  (:require [clojure.string :as string]))

(defn code2struc [[suit rank]]
  {:suit ({\S :spade \H :heart \D :diamond \C :club} suit)
                                        ;Rank: 2..9, 10 ("T"), Jack, Queen, King, and Ace -> here 0..12
   :rank (let [ascii (int rank)]
           (if (<= ascii (int \9))
             (- ascii (int \2))
             ({\T 8 \J 9 \Q 10 \K 11 \A 12} rank)))}) 

(defn flush? [cards]
  (if (= 1 (count (keys (group-by :suit cards))))
    cards))

;; ({:suit :heart, :rank 12} {:suit :heart, :rank 11} {:suit :heart, :rank 10} {:suit :heart, :rank 9} {:suit :heart, :rank 8})

;; (defn straight? [cards]
;;   (if (and
;;        (= 5 (count (into #{} cards)))
;;        (= 4 (- (reduce min (map :rank cards))
;;                (reduce max (map :rank cards)))))
;;     cards))

;; (defn straight? [cards]
;;   (let [ranks (map :rank cards)]
;;     (and
;;      (= 5 (count (into #{} ranks)))
;;      (= 4 (- (apply max ranks) (apply min ranks))))))

(def cards ["HA" "HK" "HQ" "HJ" "HT"])
;; (= :straight-flush '(__ ["HA" "HK" "HQ" "HJ" "HT"]))

(def village
  [{:home :north :family "smith" :name "sue" :age 37 :sex :f :role :parent}
   {:home :north :family "smith" :name "stan" :age 35 :sex :m :role :parent}
   {:home :north :family "smith" :name "simon" :age 7 :sex :m :role :child}
   {:home :north :family "smith" :name "sadie" :age 5 :sex :f :role :child}

   {:home :south :family "jones" :name "jill" :age 45 :sex :f :role :parent}
   {:home :south :family "jones" :name "jeff" :age 45 :sex :m :role :parent}
   {:home :south :family "jones" :name "jackie" :age 19 :sex :f :role :child}
   {:home :south :family "jones" :name "jason" :age 16 :sex :f :role :child}
   {:home :south :family "jones" :name "june" :age 14 :sex :f :role :child}

   {:home :west :family "brown" :name "billie" :age 55 :sex :f :role :parent}
   {:home :west :family "brown" :name "brian" :age 23 :sex :m :role :child}
   {:home :west :family "brown" :name "bettie" :age 29 :sex :f :role :child}

   {:home :east :family "williams" :name "walter" :age 23 :sex :m :role :parent}
   {:home :east :family "williams" :name "wanda" :age 3 :sex :f :role :child}])
(def browns (filter #(= "brown" (string/lower-case (:family %)))))

(def kids (map #(if (= :child (:role %)) 1 0)))
(def xform (comp browns kids))
(transduce xform + 0 village)
(def j-first (filter #(= \j (-> % :name string/lower-case first))))
(def kids2 (filter #(= :child (:role %))))
(def make1s (map (fn [_] 1)))
(def xform2 (comp kids2 j-first make1s))
(transduce xform2 conj [] village)
(transduce xform2 + 0 village)
;; calculate the average age of children on or below the equator

(def south (filter #(= :south (:home %))))
(def south-kids (comp south kids2))
(transduce south-kids conj [] village)
(def count-south-kids (comp south-kids make1s))

(transduce count-south-kids + 0 village)

(defn my-reducer
  ([] "init w/no arg"
   [0 0])
  ([acc] "teardown"
   (/ (acc 1) (acc 0)))
  ([acc nxt] "reduction"
   [(inc (acc 0))
    (+ (acc 1) (:age nxt))]))
(transduce south-kids my-reducer [0 0] village)
(transduce south-kids my-reducer village)

(defn straight? [cards]
  (let [ranks (map :rank cards)]
    (and
     (= 5 (count (into #{} ranks)))
     (= 4 (- (apply max ranks) (apply min ranks))))))

(def new-cards (map code2struc cards))

(defn myr2
  ([]
   [#{}, java.lang.Integer/MAX_VALUE, java.lang.Integer/MIN_VALUE])
  ([acc])
  ([acc nxt]
   [(into #{} (nxt 0))])) 
(def ranks (map :rank))
(defn straight-flush? [cards]
  (some-> cards
          straight? 
          flush?))
(straight-flush? new-cards)
