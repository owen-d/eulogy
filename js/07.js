'use strict';

const _ = require('lodash');
let primeGen = require('./lib/genPrimes');

let primes = primeGen();
let res;

for (var x = 0; x < 10001; x++) {
  res = primes.next().value;
  console.log(x, res)
}

console.log(res)
