(ns clj.15)

;; Starting in the top left corner of a 2×2 grid, and only being able to move to the right and down, there are exactly 6 routes to the bottom right corner.

(defn lattice-paths
  ""
  ([n]
   (lattice-paths n 0 0))
  ([n x y]
   (cond (or (> x n) (> y n)) 0
         (and (= x n) (= y n)) 1
         :else (+ (lattice-paths n (+ x 1) y)
                  (lattice-paths n x (+ y 1))))))

(lattice-paths 20)
