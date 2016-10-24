'use strict';
const _ = require('lodash');

function* erastosthenes() {
  let base = 2;
  let gates = [];
  let res = [];
  function newPrime(num, gates) {
    return _.reduce(gates, (accum, gate) => !accum ? accum : gate(accum), num)
  }
  function newGate(num) {
    return newNum => newNum % num === 0 ? false : newNum;
  }

  while (true) {
    let test = newPrime(base, gates);
    base++;
    if (test) {
      gates.push(newGate(test))
      yield test;
    }
  }
}

module.exports = erastosthenes;
