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

module.exports = fib;
