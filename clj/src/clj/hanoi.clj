(ns hanoi)


;; ;; move n - 1 onto pivot
;; [src pivot dst] = hanoi (src pivot dst (- n 1))
;; ;; move 1 from src onto dst
;; [src dst pivot] = (rest src) (cons (first src) dst) pivot
;; ;; move n - 1 onto dst from pivot
;; [pivot dst src] = hanoi (pivot dst src (- n 1))
;; ;; return [src dst pivot]


(defn hanoi
  ""
  ([src dst pivot] (hanoi src dst pivot (count src)))
  ([src dst pivot n]
   (if (= n 0)
     [src dst pivot]
     (let [[src pivot dst] (hanoi src pivot dst (- n 1))
           [src dst pivot] [(rest src) (cons (first src) dst) pivot]
           [pivot dst src] (hanoi pivot dst src (- n 1))]
       [src dst pivot]))))

(hanoi [1 2 3 4 5] [] [])
