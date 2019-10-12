<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String domain = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="Xenon Boostrap Admin Panel" />
<meta name="author" content="" />
<title>自动化测试系统</title>
<link rel="Shortcut Icon" href="<%=path%>/images/favicon.ico" />
<link rel="stylesheet"
	href="<%=path%>/css/fonts/fontawesome/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=path%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=path%>/css/xenon-core.css">
<link rel="stylesheet" href="<%=path%>/css/animation.css">
<link rel="stylesheet" href="<%=path%>/css/tou-style.css">
<style type="text/css">
	.modal-dialog.xinzm{
		width:98%;
	}
	.modal .modal-dialog .modal-content{
		width:100%;
	}
	.xzmain{
		width:100%  !important;
	}
	.modal .xinzm .xzmain .bwarp{
		width:100%;
	}
</style>
<script src="<%=path%>/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/common/page.js"></script>
<script type="text/javascript">
	var path='<%=path%>';
	function queryresult(id){
		var data={};
		data.executionId=id;
		var url=path+"/testexecution/selectResultByExecutionId.do";
		// ajaxReq(data, url, showSingle, "post");
		$.post(url, data, function(res) {
			$(".xinz").modal("show");
			showSingle(res);
		});
	}
	function hrefResultList(id){
		window.location.href=path+"/testexecution/selectResultByExecutionId.do?executionId="+id
	}
	function hrefFailureResultList(id){
		window.location.href=path+"/testexecution/selectFailureResultByExecutionId.do?executionId="+id
	}
	function hrefUnrunResultList(id){
		window.location.href=path+"/testexecution/selectUnrunResultByExecutionId.do?executionId="+id
	}
	
	function showSingle(data){
		$("#table_list2").html(data);
		//$("#table_list2").val(data);
		//var a = $("#initmethodselect").val();
		//var b = $("#initmethodselect").find("option:selected").html();
		//$("#methodselect2 option").val(a);
		//$("#methodselect2 option").html(b);
		//$("#suite_id2").val(data.suite_id);
		//$("#suite_des2").val(data.suite_des);
		//$("#suite_isrun2").val(data.is_run);
	}
</script>
</head>
<body class="page-body">
<input type="hidden" value="${params }" id="skipParam">
	<div class="page-container">
		<!--progress-->
		<%@include file="../common/left-menu.jsp"%>
		<div class="main-content">
			<div class="topv">
				<h3>测试报告</h3>
			</div>
			<div class="row" style="padding-top: 50px;">
				<div class="col-md-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<!--<div class="panel-options"> <a href="#"> <i class="linecons-cog"></i> </a> <a href="#" data-toggle="panel"> <span class="collapse-icon">–</span> <span class="expand-icon">+</span> </a> <a href="#" data-toggle="reload"> <i class="fa-rotate-right"></i> </a> <a href="#" data-toggle="remove"> × </a> </div>-->
						</div>
						<div class="table-responsive" id="table_list1">
							<jsp:include page="list.jsp" />
						</div>
						<%@include file="../common/page.jsp" %>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 查看结果 -->
	<div class="modal fade xinz" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel">
		<div class="modal-dialog xinzm">
			<div class="modal-content">
				<div class="xzmain">
					<h3>查看结果</h3>
					<div class="bwarp">
						<div class="table-responsive" id="table_list2">
							<%-- <jsp:include page="resultlist.jsp" /> --%>
						</div>
						<div class="modal-footer">
							<p>
								<button type="button" class="closebtn" data-dismiss="modal">确定</button>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Bottom Scripts -->
	<script src="<%=path%>/js/bootstrap.min.js"></script>
	<script src="<%=path%>/js/TweenMax.min.js"></script>
	<!--<script src="<%=path%>/js/joinable.js"></script>
	<script src="<%=path%>/js/xenon-api.js"></script>-->
	<script src="<%=path%>/js/resizeable.js"></script>
	<script src="<%=path%>/js/xenon-toggles.js"></script>
	<script src="<%=path%>/js/checkbox.js"></script>
	<!--JavaScripts initializations and stuff -->
	<script src="<%=path%>/js/xenon-custom.js"></script>
	<script src="<%=path%>/js/datePicker/wdatePicker.js"></script>
</body>
</html>