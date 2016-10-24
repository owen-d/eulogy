'use strict';
const _ = require('lodash');

function palindrome(min, max) {
  let a = max;
  let b = max;
  let result;

  while (b >= min) {
    // we'll alternate which one to decrement, so this is an easy check to keep them in sync. We decrement a until it hits the minimum, then we decrement b by 1 and set a to b.
    let cur = a * b;
    let decrementA = a >= min;

    if (isPalindrome(cur)) {
      result = result ? Math.max(result, cur) : cur;
    };

    if (decrementA) {
      a--;
    } else {
      a = b;
      b--;
    }

  }
  // no palindromes in this range
  return result || false;
}

function isPalindrome(num) {
  num = typeof num === 'string' ? num : num+'';
  // correct palindrome exit cases for even & odd length #s
  if (!num.length || num.length === 1) return true;

  // if the end characters dont match, is not a palindrome
  if (_.first(num) !== _.last(num)) return false;

  // tail -> initial clips off both ends.
  let nextCheck = _.tail(_.initial(num)).join('');
  return isPalindrome(nextCheck);
}

console.log(palindrome(100, 999))
// console.log(isPalindrome(908209))
