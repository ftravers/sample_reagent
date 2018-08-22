(ns sample-reagent.highlight-letters)

(def search-with-pattern #"o")
(def text-to-search "wood")
(def matches (re-find search-with-pattern text-to-search))
(def m2 (.exec search-with-pattern text-to-search))
(.-index m2)
;; Pattern pattern = Pattern.compile(regex);

;; Matcher matcher = pattern.matcher(text);
;; // Check all occurrences
;; while (matcher.find()) {
;;     System.out.print("Start index: " + matcher.start());
;;     System.out.print(" End index: " + matcher.end());
;;     System.out.println(" Found: " + matcher.group());
;; }

