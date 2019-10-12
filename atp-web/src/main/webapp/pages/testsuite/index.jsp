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
<script src="<%=path%>/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/common/page.js"></script>
<script type="text/javascript">
	var path='<%=path%>';
	function edit(id){
		var data={};
		data.suiteId=id;
		var url=path+"/testsuite/selectBySuiteId.do";
		// ajaxReq(data, url, showSingle, "post");
		$.post(url, data, function(res) {
			showSingle(res);
			$(".edit").modal("show");
		});
	}
	function showSingle(data){
		var a = $("#initmethodselect").val();
		var b = $("#initmethodselect").find("option:selected").html();
		$("#methodselect2 option").val(a);
		$("#methodselect2 option").html(b);
		$("#suite_id2").val(data.suite_id);
		$("#suite_des2").val(data.suite_des);
		$("#suite_isrun2").val(data.is_run);
	}
	function update(){
		var data = {};
		data.suite_id = $("#suite_id2").val();
		data.suite_des = $("#suite_des2").val();
		data.is_run = $("#suite_isrun2").val();
		var url = path + "/testsuite/updateById.do";
		// ajaxReq(data, url, doCall, "post");
		$.post(url, data, function(res) {
			$("#methodname4").html(res.obj);
			$(".xinxi").modal('show');
		});
	}
	function del(id,name) {
		$(".dele").modal("show");
		$("#methodname3").html(name);
		$("#delyes").unbind("click").click(function() {
			$('.dele').modal('hide');
			var data = {};
			data.sid = id;
			data.sname = name;
			var url = path + "/testsuite/deleteById.do";
			ajaxReq(data, url, doCall, "post");
		});
	}
	function selectChangeService(event){
		var value=$(event).val();
		var url=path+"/testmethod/selectByServiceId.do";
		var data={sid:value};
		ajaxReq(data, url, selectChangeServiceCall, "post");
	}
	function selectChangeServiceCall(result){
		var data = result;
		var options = "";
		for(var i=0;i<data.length;i++){
			options+=("<option value='"+data[i].method_id+"'>"+data[i].method_name+"</option>");
		}
		$("#initmethodselect").html(options);
		
		query();
	}
	function query(){
		var data={};
		data.sid=$("#initmethodselect").val();
		var url=path+"/testmethod/selectMethodId.do";
		// ajaxReq(data, url, query_result, "post");
		// $.post(url, data, function(res) {
		//	query_result(res);
		// });
		//$(".edit").modal("show");
		$("#payForm").submit();
	}
	function query_result(data) {
		$("#table_list").html(data);
	}
	function selectChangeMethod(event){
		var value=$(event).val();
		var url=path+"/testsuite/createdMethod.do";
		var data={sid: value};
		// ajaxReq(data, url, selectChangeServiceCall, "post");
		query();
	}
	function chBlur(a, b, c) {
		if ($("#" + a).val() == "") {
			$("#" + b).html(c)
			return false;
		}
		$("#" + b).html("")
		return true;
	}
	function doCall(result){
		$("#methodname4").html(result.obj);
		$(".xinxi").modal('show');
	}
	function addBefore() {
		$(".xinz").modal("show");
		var a = $("#initmethodselect").val();
		var b = $("#initmethodselect").find("option:selected").html();
		$("#methodselect option").val(a);
		$("#methodselect option").html(b);
	}
	function add() {
		if ($("#methodid1").val() == "") {
			alert("测试用例集不能为空");
			return;
		}
		if ($("#suite_des").val() == "") {
			alert("用例描述不能为空");
			return;
		}
		var data = {};
		data.method_id = $("#methodid1").val();
		data.suite_des = $("#suite_des").val();
		data.is_run = $("#suite_isrun").val();
		var url = path + "/testsuite/add.do";
		ajaxReq(data, url, doCall, "post");
	}
	function run(id,name) {
		var data = {};
		data.sid = id;
		data.sname = name;
		var url = path + "/testsuite/run.do";
		//ajaxReq(data, url, doCall, "post");
		$.post(url, data, function(res) {
			$("#runmsg").html(res.message);
			$("#runmsgexecutionid").val(res.obj.execution_id);
			$("#runmsgexecutionid").html(res.obj.execution_id);
			$(".zhixing").modal('show');
		});
	}
	function skipIndex(){
		if (typeof($("#method_id").html()) == "undefined"){ 
			window.location.href=path+"/testsuite/index.do"
		} else {
			var id = $("#method_id").html();
			window.location.href=path+"/testsuite/selectByMethodIdToSuite.do?sid="+id
		}
	}
	function hrefExecutionIndex(){
		var id = $("#runmsgexecutionid").val();
		window.location.href=path+"/testexecution/selectByExecutionId.do?sid="+id
	}
	function hrefCaseIndex(id){
		window.location.href=path+"/testcase/selectBySuiteIdToCase.do?sid="+id
	}
	function addBefore2() {
		var a = $("#method_id").html();
		$("#methodid1").val(a);
		$("#methodid1").html(a);
		$(".xinz").modal("show");
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
				<h3>测试用例</h3>
			</div>
			<div class="row" style="padding-top: 50px;">
				<div class="col-md-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<!--<div class="panel-options"> <a href="#"> <i class="linecons-cog"></i> </a> <a href="#" data-toggle="panel"> <span class="collapse-icon">–</span> <span class="expand-icon">+</span> </a> <a href="#" data-toggle="reload"> <i class="fa-rotate-right"></i> </a> <a href="#" data-toggle="remove"> × </a> </div>-->
						</div>

						<div class="screen">
						<form action="<%=path %>/testsuite/index.do" id="payForm" method="post">
							<ul class="shaix clearfix">
								<!-- 
								<li><span>服务名称：</span> <select name="initserviceselect" id="initserviceselect" onchange="selectChangeService(this)">
										<c:forEach items="${initServiceList }" var="item" >
											<option value="${item.service_id }" <c:if test="${param.initserviceselect == item.service_id }">selected</c:if> >${item.service_name }</option>
										</c:forEach>
								</select></li>
								<li><span>测试用例集：</span> <select name="initmethodselect" id="initmethodselect" onchange="selectChangeMethod(this)">
										<c:forEach items="${initMethodList }" var="item">
											<option value="${item.method_id }" <c:if test="${param.initmethodselect == item.method_id }">selected</c:if> >${item.method_name }</option>
										</c:forEach>
								</select></li>
								 -->
								<li>
								<c:forEach var="item" items="${methodInfo }" >
									<span><strong id="method_id"  style="display:none">${item.method_id } </strong></span>
									<span><strong>*用例集：</strong><strong id="method_name" >${item.method_name } </strong></span>
									<span><strong id="method_des" style="display:none">${item.method_des }</strong></span>
								</c:forEach>
								</li>
								<li>
									<button type="button" class="addnew" onclick="addBefore2()">新增测试用例</button>
								</li>
							</ul>
						</form>
						</div>

						<div class="table-responsive" id="table_list">
							<jsp:include page="list.jsp" />
						</div>
						<%@include file="../common/page.jsp" %>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 新增 -->
	<div class="modal fade xinz" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel">
		<div class="modal-dialog xinzm">
			<div class="modal-content">
				<div class="xzmain">
					<h3>用例组-新增</h3>
					<div class="bwarp">
						<ul>
							<li class="clearfix bgwhite">
								<div class="fp">
									<span><strong>*</strong>模块Id:</span><input type="text" id="methodid1"  disabled="disabled" />
									<!-- <span><strong>*</strong>测试用例集ID:</span> <select id="methodselect" ><option value=""></option></select>  -->
								</div>
								
							</li>
							<li class="clearfix bgwhite">
								<div class="fp">
									<span><strong>*</strong>用例组描述:</span> <input type="text" id="suite_des" onblur="chBlur('suite_des','case_des_span','方法名称不能为空')"/><span id="case_des_span" style="color: red;font-size:13px"></span>
								</div>					
								<div class="fp">
									<span><strong>*</strong>是否运行:</span>
									<select id="suite_isrun" >
										<option value="0">NO</option>
										<option value="1">YES</option>
									</select>
								</div>
							</li>
						</ul>
						<div class="modal-footer">
							<p>
								<button type="button" class="save" onclick="add()">保存</button>
								<button type="button" class="closebtn" data-dismiss="modal">取消</button>
							</p>
						</div>


					</div>
				</div>

			</div>
		</div>
	</div>

	<!-- 修改  TODO -->
	<div class="modal fade edit" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel">
		<div class="modal-dialog xinzm">
			<div class="modal-content">
				<div class="xzmain">
					<h3>测试用例-编辑</h3>
					<div class="bwarp">
							<ul>
							<li class="clearfix bgwhite">
								<div class="fp">
									<span><strong>*</strong>测试用例ID:</span> <input type="text" id="suite_id2" disabled="disabled"/>
								</div>
							</li>
							<li class="clearfix bgwhite">
								<div class="fp">
									<span><strong>*</strong>测试用例描述:</span> <input type="text" id="suite_des2" onblur="chBlur('suite_des2','suite_des_span2','用例描述不能为空')"/><span id="suite_des_span2" style="color: red;font-size:13px"></span>
								</div>
														
								<div class="fp">
									<span><strong>*</strong>是否运行:</span>
									<select id="suite_isrun2" >
										<option value="0">NO</option>
										<option value="1">YES</option>
									</select>
								</div>
							</li>
							</ul>
						<div class="modal-footer">
							<p>
								<button type="button" class="save" onclick="update()">编辑</button>
								<button type="button" class="closebtn" data-dismiss="modal">取消</button>
							</p>
						</div>


					</div>
				</div>

			</div>
		</div>
	</div>
	
	<!-- 修改成功后的提示 -->
	<div class="modal fade xinxi" id="" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">信息提示</h4>
				</div>
				<div class="modal-body">
					<div class="xints">
						<h4 id="msg"></h4>
						<p>
							<span>用例:<strong id="methodname4"></strong></span>
						</p>
					</div>
				</div>
				<div class="modal-footer">
					<p>
						<button type="button" class="save" data-dismiss="modal" onclick="skipIndex()">确认</button>
					</p>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 删除提示 -->
	<div class="modal fade dele" id="" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel2">信息提示</h4>
				</div>
				<div class="modal-body">
					<div class="xints">
						<h4>是否删除</h4>
						<p>
							<span>测试方法:<strong id="methodname3"></strong></span>
						</p>
					</div>
				</div>
				<div class="modal-footer">
					<p>
						<button type="button" class="save" id="delyes">是</button>
						<button type="button" class="closebtn" data-dismiss="modal">取消</button>
					</p>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 执行成功后的提示 -->
	<div class="modal fade zhixing" id="" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel3">信息提示</h4>
				</div>
				<div class="modal-body">
					<div class="xints">
						<h4 id="msg2"></h4>
						<p>
							<span>用例:<strong id="runmsg"></strong></span>
							<strong id="runmsgexecutionid" style="display:none">${caseList }</strong>
						</p>
					</div>
				</div>
				<div class="modal-footer">
					<p>
						<button type="button" class="save" data-dismiss="modal" onclick="hrefExecutionIndex()">去查看</button>
						<button type="button" class="closebtn" data-dismiss="modal">取消</button>
					</p>
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