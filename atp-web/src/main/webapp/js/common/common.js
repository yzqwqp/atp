function ajaxReq(data,url,call,type){
	if(type==null){
		type="post";
	}
	$.ajax({
		"type":type,
		"url":url,
		"traditional": true,
		"data":data,
		 dataType: "json",
		 beforeSend: function () {
		        // 禁用按钮防止重复提交
//			 if(typeof(content.divId)!="undefined" && content.divId!=null){
//				 $("<div id='div_brg0001'></div>").css({
//					 position:'absolute',
//					 top:0,
//					 left:0,
//					 backgroundColor:"#004400",
//					 opacity:0.1,
//					 zIndex:300
//					 }).height($(document).height()).width($(document).width()).hide().appendTo($("#"+content.divId));
//				 $("#div_brg0001").show();
//			 }
		 },
		"success":function(result){
//			result=eval("("+result+")");
//			try{
//				result=eval("("+result+")");
//			}catch(e){
//				
//			}
			call(result);
		},
		complete: function () {
//			if(typeof(content.divId)!="undefined" && content.divId!=null){
//				$("#div_brg0001").remove();
//			}
	    },
//		error:function(){
//			alert("请求失败");
//		}
	});
}