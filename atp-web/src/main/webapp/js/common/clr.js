function checkAll(){
	var a=document.getElementsByName("nameall");
	var obj=document.getElementsByName("namea");
	if(typeof(obj)=='undefined'||obj==null){
		alert("没有可以选择的数据");
		return;
	}
	if(a[0].checked){
		for(var i=0;i<obj.length;i++){
			if(!obj[i].checked){
				obj[i].checked=true;
			}
		}
	}else{
		for(var i=0;i<obj.length;i++){
			if(obj[i].checked){
				obj[i].checked=true;
			}
		}
	}
}
function checkAllTa(){
	var a=document.getElementsByName("tacheck");
	var obj=document.getElementsByName("tanos");
	commonChoise(a, obj);
}
function commonChoise(a,obj){
	if(typeof(obj)=='undefined'||obj==null){
		alert("没有可以选择的数据");
		return;
	}
	if(a[0].checked){
		$(a).parent().parent().addClass("chos");
		for(var i=0;i<obj.length;i++){
			if(!obj[i].checked){
				$(obj[i]).parent().parent().addClass("chos");
				obj[i].checked=true;
			}
		}
	}else{
		$(a).parent().parent().removeClass("chos");
		for(var i=0;i<obj.length;i++){
			if(obj[i].checked){
				$(obj[i]).parent().parent().removeClass("chos");
				obj[i].checked=false;
			}
		}
	}
}
function submitCheck(){
	var obj=document.getElementsByName("tanos");
	if(typeof(obj)=='undefined'||obj==null){
		alert("没有可以查询的ta");
		return false;
	}
	var ischecked=false;
	for(var i=0;i<obj.length;i++){
		if(obj[i].checked){
			ischecked=true;
			break;
		}
	}
	if(!ischecked){
		alert("请先选择要查询的ta");
		return false;
	}
	return true;
}
function getData(){
	var data={};
	var obj=document.getElementsByName("namea");
	var a=document.getElementsByName("nameall");
	if(a[0].checked){
		data.all="1";
	}else{
		data.all="0";
	}
	var ss="";
	var f=false;
	if(typeof(obj)=='undefined'||obj==null){
		alert("请先选择要处理的数据");
		return null;
	}
	for(var i=0;i<obj.length;i++){
		if(obj[i].checked){
			f=true;
			ss+=obj[i].value+",";
		}
	}
	if(!f){
		alert("请先选择要处理的数据");
		return null;
	}
	data.tanos=ss;
	return data;
}

function getSubmitData(){
	var data;
	if($("#nameallid").is(':checked') || $("#nameallid").parent().hasClass('chos')){
		data="allflag=1";
	}else{
		data="allflag=0";
	}
	var f=false;
	$("[name='namea']").each(function(){
		if ($(this).is(':checked') || $(this).parent().hasClass('chos')) {
			f=true;
			data=data+"&bankserialno="+$(this).val();
		}
	});
	if(!f){
		alert("请先选择要处理的数据");
		return null;
	}
	return data;
}