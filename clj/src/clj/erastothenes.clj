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

(defn test-prime
  [cur, sieve]
  (reduce #(let [current (%2 %1)]
             (or current (reduced current))) cur sieve))

(defn recursive-primes
	[upperbound cur sieve]
	(cond
                                        ; we're done: return all of the gates
		(> cur upperbound) (map #(%) sieve)
                                        ; Check if it makes it through the sieve
		(boolean (test-prime cur sieve)) (recursive-primes upperbound (conj sieve (modu-limit cur)) (+ cur 1))
    ; not a prime, check next
		:else (recur upperbound sieve (+ cur 1))))

(defn build-primes
  "easy prime instantiation"
  ([]
   (build-primes 100)
   )
  ([upperbound]
   (recursive-primes upperbound [] 2)
   ))



; i need a function which returns the next prime in addition to the sieve created.
(defn lazy-primes
  "generate prime sequences lazily"
  ([]
   (lazy-primes [2 []]))
  ([[last-prime sieve]]
   (iterate (fn workpls
              [[cur sieve]]
              (if
                  ;is a prime
                  (boolean (test-prime cur sieve)) [cur (conj sieve (modu-limit cur))]
                  ;not a prime, find the next
                  (#(if (test-prime %1 %2) %1 (recur (inc %1) %2)) (inc cur) sieve)))
            [last-prime sieve])))

(lazy-primes)


