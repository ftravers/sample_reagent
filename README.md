If you dont use emacs, then in a terminal do:

```clojure
    $ lein figwheel
      (... cruft ...)
    dev:cljs.user=> (in-ns 'sample-reagent.core)
      nil
    dev:sample-reagent.core!{:conn 2}=> (load "core")
      nil
    dev:sample-reagent.core!{:conn 2}=> (swap! app-state assoc :text "a new string...")
      {:text "a new string..."}
```
