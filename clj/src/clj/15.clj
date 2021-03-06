(ns clj.15)

;; Starting in the top left corner of a 2×2 grid, and only being able to move to the right and down, there are exactly 6 routes to the bottom right corner.

(defn lattice-paths
  ""
  ([n]
   (lattice-paths n 0 0 {}))
  ([n x y store]
   (let [found (get store [x y])]
     (cond (or (> x n) (> y n)) [store 0]
           (and (= x n) (= y n)) [store 1]
           ;; if we already have a memoized # of subpaths from here, use it!
           found [store found]
           :else
           ;; otherwise, branch
           (let [[first-store first-sum] (lattice-paths n (+ x 1) y store)
                 [second-store second-sum] (lattice-paths n x (+ y 1) first-store)]
             [(assoc second-store [x y] (+ first-sum second-sum)) (+ first-sum second-sum)])))))


(second (lattice-paths 20))


;; NOTE:
;; you can use the equation n choose r = n!/(r!(n-r)!)
;; this comes from the realization that there must be 40 moves total for a 20x20 board
;; to get from 0,0 -> 20,20
;; These can be composed of any combinations of 20 downs and 20 rights, thus 40 choose 20:
;; 40!/20
