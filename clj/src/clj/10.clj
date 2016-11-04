(ns clj.10)
(require '[clj.erastothenes :as erastothenes :refer [lazy-primes]])


(defn sum-primes-under-lim
  ([]
   (sum-primes-under-lim 1000))
  ([lim]
   (reduce + 
           (take-while #(< % lim) (lazy-primes)))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (sum-primes-under-lim 10)))
