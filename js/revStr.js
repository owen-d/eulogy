'use strict';
const expect = require('chai').expect;

// revSimple iterates from the end, appending to a newly created string. does not mutate, O(n) in time & space
function revSimple(str) {
  let res = '';
  for (var i = str.length - 1; i >= 0; i--) {
    res += str[i];
  }
  return res;
};

// revInPlace holds two ptrs, & inc/decrements them, swapping elements until they meet in the middle. O(1) space, O(n/2) time.
// note, space is actually O(n), because strings are immutable in js, so we coerce to an array for index manipulation
function revInPlace(str) {
  if (str.lenth === 0) {
    return str;
  }
  let arrStr = str.split('');
  let ptrA = 0;
  let ptrB = str.length - 1;
  while (ptrA <= ptrB) {
    let swap = arrStr[ptrA];
    arrStr[ptrA] = arrStr[ptrB];
    arrStr[ptrB] = swap;
    ptrA++;
    ptrB--;
  }
  return arrStr.join('');
};

// revRecur is a recursive implementation. It builds a deferred operation process & uses the callstack to descend into smaller & smaller substrings,
// using them as prefixes. O(n^2) space due to string slicing (js strings are immuatable), which creates duplicates and O(n) time.
// note: I'm unclear on the internal representation of JS strings, but it's reasonable they could be using something clever under the hood, like
// clojure does. In the clojure implementation's case this would change it to O(n) space complexity.
// see http://hypirion.com/musings/understanding-persistent-vector-pt-1 for more info
function revRecur(str) {
  if (str.length < 2) {
    return str;
  } else {
    return revRecur(str.slice(1)) + str[0];
  }
};
// ----- testing

const testCases = [
  ["", ""],
  ["abc", "cba"],
  ["abacus", "sucaba"]
];

[revSimple, revInPlace, revRecur].map(fn => {
  describe(fn.name, () => {
    testCases.map(([input, res]) => {
      it(`should reverse ${input}`, () => {
        expect(fn(input)).to.equal(res);
      });
    });
  });
});
