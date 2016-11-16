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

(defn find-node
  [[& coords] sub-matrix]
  (if (or
       (empty? coords)
       (not sub-matrix))
    ;we've descended correctly or have reached a boundary so return node/zero.
    (or sub-matrix 0)
    ;otherwise descend
    (recur (rest coords) (get sub-matrix (first coords)))))

(defn traverse-next-coords
  [directions coords]
  (map + directions coords))

(defn get-traverse-right-coords
  [coords]
  ((partial traverse-next-coords [0 1]) coords))

(defn get-traverse-down-right-coords
  [coords]
  ((partial traverse-next-coords [1 1]) coords))

(defn get-traverse-down-coords
  [coords]
  ((partial traverse-next-coords [1 0]) coords))

(defn get-traverse-down-left-coords
  [coords]
  ((partial traverse-next-coords [1 -1]) coords))

(defn multiply-nodes
  "adds consecutive nodes in a given direction in a matrix"
  ([direction-fn matrix init-node-coords]
   (multiply-nodes direction-fn matrix init-node-coords 4 0))
  ([direction-fn matrix init-node-coords count sum]
   (if (= count 0)
     ;done, return sum
     sum
     (recur direction-fn matrix (direction-fn init-node-coords) (dec count) (+ sum (find-node init-node-coords matrix))))))

(defn get-all-coords
  ([matrix]
   (get-all-coords matrix []))
  ([matrix coords]
   (if (number? matrix)
     coords
     (map-indexed #(get-all-coords %2 (conj coords %1))
                  matrix))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  println (let
              [split-str (build-matrix-from-string ["\n" " "] (get-text))
               int-matrix (deep-map #(. Integer parseInt %1) clojure.lang.PersistentVector split-str)
               node-list (reduce #(concat %1 %2) (get-all-coords int-matrix))]
            node-list))
;(multiply-nodes get-traverse-down-coords int-matrix [0 0])
