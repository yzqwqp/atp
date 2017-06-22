$(document).ready(function() {
	var str = "<div class='modal fade oplog' tabindex='-1' role='dialog' aria-labelledby='mySmallModalLabel'>";
	str += "<div class='modal-dialog journala'>";
	str += "<div class='modal-content'>";
	str += "<div class='panel panel-default'>";
	str += "<div class='panel-heading'>";
	str += "<h3 class='panel-title'>系统处理日志</h3>";
	str += "<div class='panel-options'>";
	str += "<input type='hidden' id='bizcls'>";
	str += "<a href='javascript:void(0)' onclick='refreshlog()'> <i class='fa-rotate-right'></i>刷新</a>";
	str += "<a data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span>关闭</a>";
	str += "</div>";
	str += "</div>";
	str += "<div class='main-panel'>";
	str += "<table class='table table-bordered table-hover' id='logtableid'>";
	str += "</table>";
	str += "</div>";
	str += "</div>";
	str += "</div>";
	str += "</div>";
	str += "</div>";
	$('body').append(str);
});

function refreshlog(){
	$("#logtableid").html("");	
	var url=path+"/inorrect/query.do";
	var data={};
	data.bizcls=$("#bizcls").val();
	ajaxReq(data, url, logcall, "get");
	
}
function logcall(data){
	if(data!==null&&data.length>0){
		var str="";
		var time;
		var content;
		var msg;
		for(var i=0;i<data.length;i++){
			time = new Date(data[i].createtime);
			content=data[i].content;
			msg=data[i].msg;
			if(content==null){
				content="";
			}
			if(msg==null){
				msg="";
			}
			str+="<tr>";
			str+="<td>"+time.Format("yyyy年MM月dd日 hh:mm:ss");+"</td>"
			str+="<td>"+content+"</td>"
			str+="<td>"+msg+"</td>"
			str+="</tr>";
		}
		$("#logtableid").html(str);	
	}
}
function queryoplog(bizcls){
	if(bizcls==null){
		alert("业务类别不能为空");
		return;
	}
	$(".oplog").modal("show");
	$("#bizcls").val(bizcls);	
	refreshlog();
}
