// 数据容器
var left = 0; //被除数
var right = 0; //除数
var sum = 0; //和  

var numb = 0; //此变量用来限制点的输入
function $(id) {
	return document.getElementById(id);
}
// 数字盘函数
function figure(id) {

	// 判断被除数是否有值
	if (left === 0) {
		// 改变value默认值
		if ($("box").value === "0") {
			$("box").value = $(id).value;
		} else {
			$("box").value = $("box").value + $(id).value;
		}
	} else {
		$("box").value = $("box").value + $(id).value;
		var str = $("box").value;
		var num = "";
		// 获取第二次输入的数字
		for (var i = 0; i < str.length; i++) {
			// 判断加减乘除
			if (str[i] === "+") {
				for (var j = i + 1; j < str.length; j++) {
					num += str[j];

				};
				right = parseFloat(num);
			} else if (str[i] === "-") {
				for (var j = i + 1; j < str.length; j++) {
					num += str[j];

				};
				right = parseFloat(num);
			} else if (str[i] === "*") {
				for (var j = i + 1; j < str.length; j++) {
					num += str[j];

				};
				right = parseFloat(num);
			} else if (str[i] === "/") {
				for (var j = i + 1; j < str.length; j++) {
					num += str[j];

				};
				right = parseFloat(num);
			}
		};
	}

	// 清空所有数据  
	if (sum !== 0) {
		left = 0;
		right = 0;
		sum = 0;
		numb = 0;
		$("box").value = $(id).value;
	}

}

// 获取id并返回
// 运算函数
function operation(id) {
	if ($("box").value !== "0") {
		if (left === 0) {
			$("box").value = $("box").value + $(id).value;
			left = parseFloat($("box").value);
		}
	}

	//numb 转为number类型 让点可以再输入一次
	numb = 0;
}
