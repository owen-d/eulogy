(ns clj.11)
(require '[clj.erastothenes :as erastothenes :refer [lazy-primes]])

(defn get-text [] (slurp "resources/10.txt"))

(defn build-matrix-from-string
  [[& seps] string]
  (let [separated (clojure.string/split string (re-pattern  (first seps))) others (rest seps)]
    (mapv
     #(if (empty? others)
        %1
        (build-matrix-from-string others %1))
     separated)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (build-matrix-from-string ["\n" " "] (get-text))))
