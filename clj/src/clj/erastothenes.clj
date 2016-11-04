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
                                        ; if passed no argument, we're on the final step.
                                        ; Return the original val this gate was made from,
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


(defn recursive-next-prime
  [[cur sieve]]
  (if
      ;is a prime
      (boolean (test-prime cur sieve)) [cur (conj sieve (modu-limit cur))]
      ;not a prime, find the next
      (recur [(inc cur) sieve])))

; i need a function which returns the next prime in addition to the sieve created.
(defn lazy-primes
  "generate prime sequences lazily"
  ([]
   ; we know 2 is the first prime, so instantiate with 2 and the gate for 2
   (lazy-primes [2 [(modu-limit 2)]]))
  ([[cur sieve]]
   (map #(first %)
        (iterate recursive-next-prime
                 [cur sieve]))))

(defn frontload-primes
  [lim]
  (let [potentials (take (- lim 2) (iterate inc 2))]
    (loop [possibilities potentials prev-primes []]
       (if (empty? possibilities)
         ;no more #s in list, return!
         prev-primes
         (let [next-prime (first possibilities)]
           (recur (filter #(not= (mod % next-prime) 0)
                          possibilities)
                  (conj prev-primes (first possibilities))))))))

(defn sum-primes-under-lim
  ([]
   (sum-primes-under-lim 2000000))
  ([lim]
   (reduce +
           (take-while #(< % lim) (lazy-primes)))))
