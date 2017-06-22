function doSpanExp(item,exp){
	var flag=true;
	var value=item.value;
	try {
		var reg=eval("("+exp+")");
		var chk = reg.test(value);
	} catch (e) {
		alert("exp属性有误,"+e);
		flag=false;
	}
	if(!chk){
		flag=false;
		if(!$(item).hasClass("waring")){
			$(item).addClass("waring");
		}
	}else{
		if($(item).hasClass("waring")){
			$(item).removeClass("waring");
		}
	}
	return flag;
}
function checkByHJ(formId){
	var flag=true;
	var form=document.getElementById(formId);
	var inputs=form.getElementsByTagName("input");
	var selects=form.getElementsByTagName("select");
	for(var i=0;i<selects.length;i++){
		var item=selects[i];
		var exp=item.getAttribute("exp");
		if(exp==null||exp==""){
			continue;
		}
		var chk;
		try {
			var reg=eval("("+exp+")");
			chk = reg.test(item.value);
		} catch (e) {
			alert("exp属性有误:"+e);
			chk=false;
		}
		if(!chk){
			flag=false;
			if(!$(item).hasClass("waring")){
				$(item).addClass("waring");
			}
		}else{
			if($(item).hasClass("waring")){
				$(item).removeClass("waring");
			}
		}
	}
	for(var i=0;i<inputs.length;i++){
		var item=inputs[i];
		var exp=item.getAttribute("exp");
		if(exp==null||exp==""){
			continue;
		}
		var chk;
		try {
			var reg=eval("("+exp+")");
			chk = reg.test(item.value);
		} catch (e) {
			alert("exp属性有误:"+e);
			chk=false;
		}
		if(!chk){
			flag=false;
			if(!$(item).hasClass("waring")){
				$(item).addClass("waring");
			}
		}else{
			if($(item).hasClass("waring")){
				$(item).removeClass("waring");
			}
		}
	}
	return flag;
}
$(function(){
	$(".bindBlur").blur(function(){
		var exp=this.getAttribute("exp");
		if(exp==null||exp==""){
			return;
		}
		doSpanExp(this, exp)
	});
});