function skipPage(){
	var reg=/^[1-9]+\d*$/;
	if(!reg.test($("#skip").val())){
		alert("请输入大于0的正整数");
		return;
	}
	var value=$("#skip").val();
	if(Number(value)>Number($("#total").text())){
		alert("不能大于总页数");
		return;
	}
	var params=$("#skipParam").val();
	var param=value+params;
	createForm(param);
}
function choisePage(){
	var current=1;
	var pagesize="&pagesize="+$("#pagesize").val();
	var params=$("#skipParam").val();
	var param=current+pagesize+params;
	createForm(param);
}
function createForm(param){
	var kv=param.split("&");
	var url=href.split("?")[0];
	var form = document.createElement("form");
	form.action=url;
	form.method="post";
	document.body.appendChild(form);
	var input = document.createElement("input");
	input.type = "hidden";
	input.value = kv[0];
	input.name = "current";
	form.appendChild(input);
	if(kv.length==1){//只有一个参数
		form.submit();
		return;
	}
	for(var i=1;i<kv.length;i++){
		var keyvalue=kv[i];
		var keyvalueArray=keyvalue.split("=");
		if(keyvalueArray.length<2||keyvalueArray[1]==""||keyvalueArray[1]==null){
			continue;
		}
		var input = document.createElement("input");
		input.type = "hidden";
		input.name = keyvalueArray[0];
		input.value = keyvalueArray[1];
		form.appendChild(input);
	}
	form.submit();
}