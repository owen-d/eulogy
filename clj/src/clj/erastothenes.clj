(ns clj.erastothenes)

(defn modu-limit
	"Builds a gating modulo fn"
	[x]
	(fn
		([a] (if (or (= a nil) (= (mod a x) 0))
                                        ; is a multiple or was a multiple of a previous gate, disregard.
           nil
                                        ; is not a multiple. Pass on to next in the list
           a
           ))
                                        ; if passed no argument, we're on the final step. Return the original val this gate was made from,
                                        ; as it's a valid prime.
		([] x)))

(defn recursive-primes
	[upperbound pipe value]
	(cond
                                        ; we're done: return all of the gates
		(> value upperbound) (map #(%) pipe)
                                        ;
		(boolean (reduce #(%2 %1) value pipe)) (recursive-primes upperbound (conj pipe (modu-limit value)) (+ value 1))
		:else (recur upperbound pipe (+ value 1))))

(defn build-primes
  "easy prime instantiation"
  ([]
   (build-primes 100)
   )
  ([upperbound]
   (recursive-primes upperbound [] 2)
   ))
