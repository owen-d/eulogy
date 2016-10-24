'use strict';
const _ = require('lodash');

function isTriplet(a, b) {
  return findC(a, b) % 1 === 0;
}

function findC(a, b) {
  return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
}

function recursiveFind(base, c=0) {
  let cSquared = Math.pow(c, 2);
  let wiggleRoom = base-c;
  let a = wiggleRoom;
  let b = wiggleRoom - a;

  while (Math.pow(findC(a, b), 2) > cSquared && a > wiggleRoom/2) {
    a--;
    b = wiggleRoom - a;
    // console.log(a, b, c, Math.pow(findC(a, b), 2), cSquared)
  }

  if (findC(a, b) === c) return [a, b, c];

  return recursiveFind(base, c+1);
}


console.log(_.reduce(recursiveFind(1000), (a, b) => a * b))
