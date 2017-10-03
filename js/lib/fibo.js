'use strict';
const _ = require('lodash');

function* fib(head=1, tail=0) {
  while (true) {
    let newHead = head + tail;
    yield newHead;
    tail = head;
    head = newHead;
  }
}


// 0, 1, 1, 2, 3, 5, 8, 13
// fib(0) -> 0, fib(2) -> 1
function fibNaive(n) {
  if (n === 0) {return 0}
  if (n === 1) {return 1}
  return fib(n-1) + fib(n-2);
}


function fibIter(n) {
  if (n === 0) {return 0};
  if (n === 1) {return 1};

  const desired = n;

  function _fibIter(a, b, n, desired) {
    if (n === desired) {
      return a + b
    } else {
      return _fibIter(b, a + b, n + 1, desired)
    }
  }

  return _fibIter(0, 1, 2, desired);
}
module.exports = fib;
