function searchBiz(event) {
	var value = $(event).val();
	var choise = new Array();
	if (value == "") {// 没有要输入的值、则显示所有的选项
		choise = data;
	} else {
		var values = value.split(",");
		for (var k = 0; k < values.length; k++) {
			if (values[k] == "") {
				continue;
			}
			for (var i = 0; i < data.length; i++) {
				var item = data[i];
				if (item.msg.indexOf(values[k]) != -1) {// 有匹配的选项、记录此选项
					choise.push(item);
				}
			}
		}
	}
	if (choise.length > 0) {
		choise = unique(choise);
	}
	writeBiz(choise);
}
function unique(arr) {
	var result = [], hash = {};
	for (var k = 0; k < arr.length; k++) {
		if (!hash[arr[k].code]) {
			result.push(arr[k]);
			hash[arr[k].code] = true;
		}
	}
	return result;
}
function writeBiz(data) {
	if (data.length == 0) {
		$("#biz").html("");
		return;
	}
	var options = "";
	var chos = $("#functiontype").val();
	var chosArray = new Array();
	if (chos != "") {
		chosArray = chos.split(",");
	}
	for (var i = 0; i < data.length; i++) {
		var item = data[i];
		var ischoise = false;
		for (var h = 0; h < chosArray.length; h++) {
			if (item.code == chosArray[h]) {
				ischoise = true;
				break;
			}
		}
		if (ischoise) {
			options += ('<label class="chos"><strong><input type="checkbox" checked="checked" value="'
					+ item.code
					+ '" onchange="choiseBox(this)" /></strong>'
					+ item.msg + '</label>');
		} else {
			options += ('<label><strong><input type="checkbox" value="'
					+ item.code + '" onchange="choiseBox(this)"/></strong>'
					+ item.msg + '</label>');
		}
	}
	$("#biz").html(options);
}
function choiseBox(event){
	var chos=$("#functiontype").val();
	if(!event.checked){//取消选中、则从选中的集合中把此选项排除
		$(event).parent().parent().removeClass("chos");
		var chosArray=chos.split(",");
		for(var i=0;i<chosArray.length;i++){
			if(chosArray[i]==event.value){
				chosArray.splice(i,1);
				break;
			}
		}
		var op="";
		for(var i=0;i<chosArray.length;i++){
			op+=(chosArray[i]);
			if(i!==chosArray.length-1){
				op+=(",");
			}
		}
		$("#functiontype").val(op);
	}else{
		//选中、则加入到选中的集合中
		$(event).parent().parent().addClass("chos");
		if(chos!=""){
			var choss=chos.split(",");
			var isexist=false;
			for(var i=0;i<choss.length;i++){
				if(choss[i]==event.value){
					isexist=true;
					break;
				}
			}
			if(!isexist){
				chos+=","+event.value;
			}
		}else{
			chos=event.value;
		}
		$("#functiontype").val(chos);
	}
	chos=$("#functiontype").val();
	var choss=chos.split(",");
	var text="";
	for(var i=0;i<choss.length;i++){
		if(choss[i]==""){
			continue;
		}
		for(var k=0;k<data.length;k++){
			var item=data[k];
			if(item.code==choss[i]){//有匹配的选项、记录此选项
				text+=item.msg+",";
			}
		}
	}
	$("#search").val(text);
}
function searchOperate(event) {
	var value = $(event).val();
	var choise = new Array();
	if (value == "") {// 没有要输入的值、则显示所有的选项
		choise = opdata;
	} else {
		var values = value.split(",");
		for (var k = 0; k < values.length; k++) {
			if (values[k] == "") {
				continue;
			}
			for (var i = 0; i < opdata.length; i++) {
				var item = opdata[i];
				if (item.msg.indexOf(values[k]) != -1) {// 有匹配的选项、记录此选项
					choise.push(item);
				}
			}
		}
	}
	if (choise.length > 0) {
		choise = unique(choise);
	}
	writeOp(choise);
}
function unique(arr) {
	var result = [], hash = {};
	for (var k = 0; k < arr.length; k++) {
		if (!hash[arr[k].code]) {
			result.push(arr[k]);
			hash[arr[k].code] = true;
		}
	}
	return result;
}
function writeOp(data) {
	if (data.length == 0) {
		$("#op").html("");
		return;
	}
	var options = "";
	var chos = $("#operatetype").val();
	var chosArray = new Array();
	if (chos != "") {
		chosArray = chos.split(",");
	}
	for (var i = 0; i < data.length; i++) {
		var item = data[i];
		var ischoise = false;
		for (var h = 0; h < chosArray.length; h++) {
			if (item.code == chosArray[h]) {
				ischoise = true;
				break;
			}
		}
		if (ischoise) {
			options += ('<label class="chos"><strong><input type="checkbox" checked="checked" value="'
					+ item.code
					+ '" onchange="choiseBoxOp(this)" /></strong>'
					+ item.msg + '</label>');
		} else {
			options += ('<label><strong><input type="checkbox" value="'
					+ item.code + '" onchange="choiseBoxOp(this)"/></strong>'
					+ item.msg + '</label>');
		}
	}
	$("#op").html(options);
}
function choiseBoxOp(event){
	var chos=$("#operatetype").val();
	if(!event.checked){//取消选中、则从选中的集合中把此选项排除
		$(event).parent().parent().removeClass("chos");
		var chosArray=chos.split(",");
		for(var i=0;i<chosArray.length;i++){
			if(chosArray[i]==event.value){
				chosArray.splice(i,1);
				break;
			}
		}
		var op="";
		for(var i=0;i<chosArray.length;i++){
			op+=(chosArray[i]);
			if(i!==chosArray.length-1){
				op+=(",");
			}
		}
		$("#operatetype").val(op);
	}else{
		//选中、则加入到选中的集合中
		$(event).parent().parent().addClass("chos");
		if(chos!=""){
			var choss=chos.split(",");
			var isexist=false;
			for(var i=0;i<choss.length;i++){
				if(choss[i]==event.value){
					isexist=true;
					break;
				}
			}
			if(!isexist){
				chos+=","+event.value;
			}
		}else{
			chos=event.value;
		}
		$("#operatetype").val(chos);
	}
	chos=$("#operatetype").val();
	var choss=chos.split(",");
	var text="";
	for(var i=0;i<choss.length;i++){
		if(choss[i]==""){
			continue;
		}
		for(var k=0;k<opdata.length;k++){
			var item=opdata[k];
			if(item.code==choss[i]){//有匹配的选项、记录此选项
				text+=item.msg+",";
			}
		}
	}
	$("#searchOp").val(text);
}

function openDiv(sno){
	var data={};
	data.sno=sno;
	var url=path+"/query/log/reqdata.do";
	ajaxReq(data, url, itemCall, "post");
}
function itemCall(json){
	$(".tbset").modal("show");
	$("#writeParam").html("");
	if(json.statusCode!="0000"){
		alert("没有查询出参数相关信息");
		return
	}else{
		var model=json.model;
		if($.trim(model)==""){
			return;
		}
		var first=model.substring(0,1);
		var data=eval("("+model+")");
		if(first=="{"){//map
			var trs="";
			for(var key in data){
				trs+=('<tr class="onea"><td>'+key+'</td><td>'+data[key]+'</td></tr>');
			}
			$("#writeParam").html(trs);
		}else if(first=="["){//list
			var trs="";
			for(var i=0;i<data.length;i++){
				var item=data[i];
				for(var key in item){
					trs+=('<tr class="onea"><td>'+key+'</td><td>'+item[key]+'</td></tr>');
				}
			}
			$("#writeParam").html(trs);
		}else{
			alert("数据格式有误,data="+model);
			return;
		}
	}
}