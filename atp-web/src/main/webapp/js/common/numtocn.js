/** 数字金额大写转换(可以处理整数,小数,负数) */
function transAmount(obj){
	var value=obj.value;
	value=value.replace(/[^\d.]/g, "");
	try{
		if(value.split(".")[1].length>2){
			value=new Number(obj.value).toFixed(2)
		}	
	}catch(e){}
	obj.value=value;
	$("#msgamount").html(MoneytoChinese(obj.value));
}

function transNumber(obj){
	var value=obj.value;
	value=value.replace(/[^\d.]/g, "");
	try{
		if(value.split(".")[1].length>2){
			value=new Number(obj.value).toFixed(2)
		}	
	}catch(e){}
	obj.value=value;
	$("#msgamount").html(NoToChinese(obj.value));
}
function initNumber(num){
	$("#msgamount").html(NoToChinese(num));
}
function initAmount(num){
	$("#msgamount").html(MoneytoChinese(num));
}
function MoneytoChinese(n) {
	if (!/^\d*(\.\d*)?$/.test(n)) { return "Number is wrong!"; } 
	var fraction = [ '角', '分' ];
	var digit = [ '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' ];
	var unit = [ [ '元', '万', '亿' ], [ '', '拾', '佰', '仟' ] ];
	var head = n < 0 ? '欠' : '';
	n = Math.abs(n);

	var s = '';

	for ( var i = 0; i < fraction.length; i++) {
		s += (digit[Math.floor(n * 10 * Math.pow(10, i)) % 10] + fraction[i])
				.replace(/零./, '');
	}
	s = s || '整';
	n = Math.floor(n);

	for ( var i = 0; i < unit[0].length && n > 0; i++) {
		var p = '';
		for ( var j = 0; j < unit[1].length && n > 0; j++) {
			p = digit[n % 10] + unit[1][j] + p;
			n = Math.floor(n / 10);
		}
		s = p.replace(/(零.)*零$/, '').replace(/^$/, '零') + unit[0][i] + s;
	}
	return head
			+ s.replace(/(零.)*零元/, '元').replace(/(零.)+/g, '零').replace(/^整$/,
					'零元整');
}

function NoToChinese(num) { 
	if (!/^\d*(\.\d*)?$/.test(num)) { return "Number is wrong!"; } 
	var AA = new Array("零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"); 
	var BB = new Array("", "拾", "佰", "仟", "萬", "億", "点", ""); 
	var a = ("" + num).replace(/(^0*)/g, "").split("."), k = 0, re = ""; 
	for (var i = a[0].length - 1; i >= 0; i--) { 
	switch (k) { 
	case 0: re = BB[7] + re; break; 
	case 4: if (!new RegExp("0{4}\\d{" + (a[0].length - i - 1) + "}$").test(a[0])) 
	re = BB[4] + re; break; 
	case 8: re = BB[5] + re; BB[7] = BB[5]; k = 0; break; 
	} 
	if (k % 4 == 2 && a[0].charAt(i + 2) != 0 && a[0].charAt(i + 1) == 0) re = AA[0] + re; 
	if (a[0].charAt(i) != 0) re = AA[a[0].charAt(i)] + BB[k % 4] + re; k++; 
	} 

	if (a.length > 1) //加上小数部分(如果有小数部分) 
	{ 
	re += BB[6]; 
	for (var i = 0; i < a[1].length; i++) re += AA[a[1].charAt(i)]; 
	} 
	if(re==""){
		re="零";
	}
	return re; 
} 