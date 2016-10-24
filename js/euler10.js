'use strict';

const _ = require('lodash');
const genPrimes = require('./lib/genPrimes');

let max = 2000000;
let primes = genPrimes();

let res = 0;
for (let x of primes) {
  if (x >= max) break;
  console.log('pushing %d', x)
  res += x;
}

console.log(res);
