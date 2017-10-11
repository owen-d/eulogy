(ns clj.14)

;; the plan is to memoize collatz calculations. Then, assuming we run into a number we've already calc'd before,
;; we can quit early (it's suspected there will be many redundant calculations)

(defn collatz-next
  "calculates n → n/2 (n is even) ||  n → 3n + 1 (n is odd)"
  [n]
  (if (even? n)
    (/ n 2)
    (+ 1 (* n 3))))

;; need map<n, steps in chain>
;; use the map to memoize results.

;; ultimately will need to return the updated map as well as the resulting chain length for n
(defn memoize-collatz
  ""
  ;; bootstrap map with 1->1
  ([n] (memoize-collatz n {1 1}))
  ([n store]
   ;; if already exists, no more computations necessary
   (let [found (get store n)]
     (if found [found store]
         ;; otherwise, we must descend until we find a memoized result, updating the map & returning the seq-length/map pair
         (let [[next-chain-ln store] (memoize-collatz (collatz-next n) store)
               cur-chain-ln (+ 1 next-chain-ln)]
           [cur-chain-ln (assoc store n cur-chain-ln)])))))


;; (memoize-collatz 4)

(defn max-collatz-seq
  "find largest collatz sequence"
  [maximum]
  ;; bootstrap map with 1->1
  (let [store {1 1}]
    (loop [n 1
           store store]
      (if (> n maximum)
        ;; find largest sequence in map
        (reduce (fn [[prev-key prev-chain-ln] [cur-key cur-chain-ln]]
                  (if (> cur-chain-ln prev-chain-ln) [cur-key cur-chain-ln]
                      [prev-key prev-chain-ln]))
                [0 0] store)
        ;; otherwise, update map & calc next
        (recur (+ n 1) (second (memoize-collatz n store)))))))

(max-collatz-seq 1000000)

;; -> 837799 with 525 items in sequence
