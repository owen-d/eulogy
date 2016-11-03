(ns clj.10)
(require '[clj.erastothenes :as erastothenes :refer [build-primes]])

; sum fn
(defn sum
  ; assuming its a sequence
  ([[a & rest]]
   (apply sum (cons a rest)))
  ([init & args]
   (reduce + init args)))

(take-while
 (#(< % 2000000))
 (build-primes 2000000))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
