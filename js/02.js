'use strict';
const _ = require('lodash');
const fib = require('./lib/fibo');

let fibber = fib();
let FOUR_MILLION = 4 * 1000000;
let desired = 15;

for (var evens = [];;) {
  if (evens.length >= desired) break;

  let next = fibber.next().value;
  if (next > FOUR_MILLION) break;
  if (next % 2 === 0) evens.push(next);
}

console.log(_.reduce(evens, (a, b) => a+b));

