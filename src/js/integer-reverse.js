const intReverse = (n) => {

  let obj = {n: n, i: 0};

  obj = recursive(obj);

  return obj.n;
};

const recursive = (obj) => {

  let y;
  
  if (obj.n < 1 && obj.n > -1) {
    return {n: 0, i: 0};
  } else {
    y = recursive({ n: Math.trunc(obj.n / 10), i: obj.i });
    y.n = y.n + (obj.n % 10) * Math.pow(10, y.i);
  }

  return {n: y.n, i: y.i + 1};

}

// 1 2 3 4
// 3 2 1 0

const intReverse2 = (n) => {

  let y = n;
  let i = 0;

  while (!(y < 1 && y > -1)) {
    y /= 10;
    i++;
  }

  y = 0;

  while (i > 0) {
    i--;
    y = y + Math.trunc(n % 10) * Math.pow(10, i);
    n = n / 10;
  }

  return y;
};

console.log(intReverse(0));
console.log(intReverse(5));
console.log(intReverse(13));
console.log(intReverse(123));
console.log(intReverse(1234));
console.log(intReverse(-123));