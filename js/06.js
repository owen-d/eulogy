'use strict';

const _ = require('lodash');

function sumSquare(a) {
  if (a === 0) return a;
  return Math.pow(a, 2) + sumSquare(a-1);
}
function squareSum(a) {
  let sum = _.reduce(_.range(a+1), (a, b) => a+b);
  return Math.pow(sum, 2);
}

console.log(Math.abs(sumSquare(10) - squareSum(10)))
