function choiseChk(event,flag){
	var parent=$(event).parent().parent();
	if(event.checked){
		if(!parent.hasClass("chos")){
			parent.addClass("chos");
		}
	}else{
		if(parent.hasClass("chos")){
			parent.removeClass("chos");
		}
	}
	var name=event.name;
	var txt="";
	var chks=document.getElementsByName(name);
	var c=false;
	for(var i=0;i<chks.length;i++){
		var item=chks[i];
		if(item.checked){
			c=true;
			txt+=$(item).parent().parent().text()+"、";
		}
	}
	var strong='<strong class="glyphicon glyphicon-triangle-bottom"></strong>';
	var h5=$(item).parent().parent().parent().prev();
	h5.html(txt+strong);
	if(flag){
		if(!c){
			h5.addClass("waring");
		}else{
			h5.removeClass("waring");
		}
	}
}