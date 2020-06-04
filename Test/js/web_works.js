var sum = 0;

function primeSum(max){
	for (var i = 2; i <= max; i++) {
		for (var j = 2; j <= i; j++) {
			if (i==j) {
				sum += i;
				postMessage(sum);
			}
			if (i % j === 0) {
				break;
			}
		}
	}
}

this.addEventListener('message', function (e) {
  primeSum(e.data);
}, false);