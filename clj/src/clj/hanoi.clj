(ns hanoi)

;; each tower is a stack, which requires smaller radii on top.
;; while x = 0; x >= 0; x++
;;   if even(x),
;;     select smallest option
;;       ;; within small selections, we alternate rotating to the small base & large bases
;;       if x % 4 == 0, rotate onto largest base
;;       else, rotate onto smallest base
;;   else odd(x),
;;     ;; note: largest option is the 2nd largest disc on top,
;;     ;; as we obviously cant move the largest top disc onto anything
;;     select largest option onto only available

(defn find-nth-smallest
  "find the nth smallest in seq"
  [n seq]
  (nth (sort seq) n))

(defn can-rotate?
  "checks if we can rotate element from on top of first stack onto second stack, returning false
  if stack1 has no top element or it cannot rotate onto stack2"
  [s1 s2]
  (let [[s1top s2top] (map first [s1 s2])]
    (cond
      ;; cannot rotate a nil disc
      (nil? s1top) false
      ;; can rotate smaller disc onto larger disc, or any disc onto empty stack
      ;; (or (nil? s2top) (> s2top s1top)) (list (rest s1) (cons s1top s2))
      (or (nil? s2top) (> s2top s1top)) true
      ;; otherwise (attempted moving larger disc onto smaller disc, return nil)
      :else false)))

;; (can-rotate? '(1) '(2 3))


(defn finished?
  "tests if 2 stacks are empty & round # > 0"
  [stacks round]
  (let [empty-stacks (count (filter #(= % 0) (map count stacks)))]
    (and (= empty-stacks 2) (> round 0))))

;; (finished? ['(1 2 3) '() '(2)] 2)


(defn compare-with-nils
  "always pushes nils to the end of the collection, hacking nil > any X, where x is an int.
  This allows us to maintain our sorted stacks, where nil bases (technically the largest) are sorted to the end"
  [a b]
  (cond (nil? b) -1
    (nil? a) 1
    :else (compare a b)))

;; (sort #(compare-with-nils (first %1) (first %2)) ['(1) () '(2 3)])


(defn round-choice
  "handler for each round iteration. returns if finished, otherwise resorts stacks & delegates to make-choice"
  ([stacks log] (round-choice stacks 0 log))
  ([stacks round log]
   ;; if we always sort the inputs, we dont have to worry about index juggling -- smallest is always first idx, etc
   (let [sorted-stacks (sort #(compare-with-nils (first %1) (first %2)) stacks)]
     (if (finished? sorted-stacks round)
       (list stacks round)
       (do (and log (println sorted-stacks round))
           (round-choice (make-choice sorted-stacks round) (inc round) log))))))


(defn make-choice
  "makes the appropriate rotation depending on round"
  [[s1 s2 s3] round]
  (if (= 0 (mod round 2))
    ;;rotate small
    (if (= 0 (mod round 4))
      ;;if x % 4 == 0, rotate onto largest base
      (if (can-rotate? s1 s3)
        (list (rest s1) s2 (cons (first s1) s3))
        nil)
      ;;else, rotate onto smallest base
      (if (can-rotate? s1 s2)
        (list (rest s1) (cons (first s1) s2) s3)
        nil))
    ;;rotate large (s2) onto s3
    (if (can-rotate? s2 s3)
      (list s1 (rest s2) (cons (first s2) s3))
      ;; otherwise return nil
      nil)))

(round-choice [[1 2 3] [] []] true)
