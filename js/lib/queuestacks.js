// blueprints for some queues based on stacks.

function Q(){
  if (!this instanceof Q) return new Q(...arguments)
  this._stack = [];
}

Q.prototype = Object.create(null);
Q.prototype.enqueue = function(a) {
  this._stack.push(a);
};
// Q.prototype.dequeue = function() {
//   if (this._stack.length < 2) {
//     return this._stack.pop();
//   } else {
//     const res = this._stack[0];
//     this._stack = this._stack.slice(1);
//     return res;
//   }
// };

Q.prototype.dequeue = function() {
  if (this._stack.length < 2) {
    return this._stack.pop();
  }

  const top = this._stack.pop();
  const res = this.dequeue();
  this._stack.push(top);
  return res;
};
