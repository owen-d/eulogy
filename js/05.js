'use strict';

const _ = require('lodash');

// function smallestMultiple(max){
//   let nums = numGen(max);
//   for (let x of nums) {
//     console.log('attempting %d', x)
//     if (divisibleBy(x, max)) return x;
//   }
// }

// function divisibleBy(a, b){
//   // base case
//   if (b === 0) return true;
//   // recur
//   return a % b === 0 ? divisibleBy(a, b-1) : false;
// }

// function* numGen(increment, base=0) {
//   var cur = base;
//   while (true) {
//     cur = cur+increment;
//     yield cur;
//   }
// }

// console.log(smallestMultiple(10))


// ------------------------ optimization ------------------------------
// need to find least common multiples...
// start at 0
// when looking for next numbers, start with the largest required denominator (20 in this case)
// find the least common multiple of 20 & 19
// then, recursively find the least common multiples like: lcm(lcm(20, 19), 18)

function* genSteps(base) {
  let cur = 0;
  while (true) {
    cur += base;
    yield cur;
  }
}
function divisiblyBy(a, b) {
  return a % b === 0;
}
function recursiveLCM(base, next=base-1) {
  // base case, all previous #s down to 0 are divisible.
  if (next === 0) return base;

  // gen our stepper
  let stepGen = genSteps(base);
  for (let x of stepGen) {
    if (divisiblyBy(x, next)) {
      return recursiveLCM(x, next-1);
    }
  }
}

console.log(recursiveLCM(20))
