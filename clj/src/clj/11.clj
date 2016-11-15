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

(defn deep-map
  [mapper type-check nested-col]
  (mapv
   #(if-not (= (type %1) type-check)
      (mapper %1)
      (deep-map mapper type-check %1))
   nested-col))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (let
               [split-str (build-matrix-from-string ["\n" " "] (get-text))
                int-matrix (deep-map #(. Integer parseInt %1) clojure.lang.PersistentVector split-str)]
             int-matrix)))
