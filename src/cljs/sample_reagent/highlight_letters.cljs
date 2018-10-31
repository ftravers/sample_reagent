(ns sample-reagent.highlight-letters)
(def regx #"o")
(def text "wood")
(def matches (re-find regx text))
(def m2 (.exec regx text))
(.-index m2)
(defn re-pos [re s]
  (let [re (js/RegExp. (.-source re) "g")]
    (loop [res {}]
      (if-let [m (.exec re s)]
        (recur (assoc res (.-index m) (first m)))
        res))))
(re-pos regx text)   

(defn sample-letter-highlight []
  [^{:key "abc"} [:option {:value "abc"}]]) 
;; Pattern pattern = Pattern.compile(regex);

;; Matcher matcher = pattern.matcher(text);
;; // Check all occurrences
;; while (matcher.find()) { 
;;     System.out.print("Start index: " + matcher.start());
;;     System.out.print(" End index: " + matcher.end());
;;     System.out.println(" Found: " + matcher.group());
;; }






