'use strict';
const _ = require('lodash');
const primeGen = require('./lib/genPrimes');

let searchVal = 600851475143;
// let max = 94827
// let primes = primeGen();

// let primesUpToMax = [];

// for (let x of primes) {
//   if (x >= max) break;
//   primesUpToMax.push(x);
// }

// let result = _.reduceRight(primesUpToMax, (accum, cur) => {
//   if (accum) {
//     return accum
//   } else {
//     return max % cur === 0 ? cur : false;
//   }
// }, false);

// console.log(result)


// more advanced
function advLPF(max=searchVal, prevMaxDivisor=0) {
  let primes = primeGen();

  for (let x of primes) {
    // base case: we've found the largest already.
    if (x > max) return prevMaxDivisor;

    //
    if (max % x === 0) return advLPF(max/x, Math.max(x, prevMaxDivisor))
  }
}
console.log(advLPF())
