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
		data.sid=id;
		var url=path+"/testmethod/selectByMethodId.do";
		// ajaxReq(data, url, showSingle, "post");
		$.post(url, data, function(res) {
			showSingle(res);
			$(".edit").modal("show");
		});
	}
	function showSingle(data){
		$("#serviceid_2").val(data.service_id);
		$("#method_id_2").val(data.method_id);
		$("#method_name_2").val(data.method_name);
		$("#method_des_2").val(data.method_des);
	}
	function update(){
		var data = {};
		data.service_id = $("#serviceid_2").val();
		data.method_id = $("#method_id_2").val();
		data.method_name = $("#method_name_2").val();
		data.method_des = $("#method_des_2").val();
		var url = path + "/testmethod/updateById.do";
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
			var url = path + "/testmethod/deleteById.do";
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
		var url=path+"/testmethod/selectById.do";
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
		var url=path+"/testmethod/selectById.do";
		var data={smid: value};
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
	function skipIndex2(){
		window.location.href=path+"/testmethod/index.do"
	}
	function skipIndex(){
		if (typeof($("#serviceid").html()) == "undefined"){ 
			window.location.href=path+"/testmethod/index.do"
		} else {
			var id = $("#serviceid").html();
			window.location.href=path+"/testmethod/selectByServiceIdToMethod.do?sid="+id
		}
	}
	function hrefSuiteIndex(id){
		window.location.href=path+"/testsuite/selectByMethodIdToSuite.do?sid="+id
	}
	function addBefore() {
		
		$(".xinz").modal("show");
		var data = {};
		var url = path + "/testmethod/addBefore.do";
		ajaxReq(data, url, addBeforecall, "get");
	}
	function addBeforecall(result) {
		var data = result;
		var options = "";
		for(var i=0;i<data.length;i++){
			options+=("<option value='"+data[i].service_id+"'>"+data[i].service_name+"</option>");
			}
		$("#allService").html(options);
	}
	function addBefore2() {
		var a = $("#serviceid").html();
		var b = $("#servicename").html();
		$("#serviceid1").val(a);
		$("#serviceid1").html(a);
		$("#servicename1").val(b);
		$("#servicename1").html(b);
		$(".xinz").modal("show");
	}
	function add() {
		if ($("#serviceid1").val() == "") {
			alert("测试模块不能为空");
			return;
		}
		if ($("#servicename1").val() == "") {
			alert("测试模块描述不能为空");
			return;
		}
		if ($("#method_name").val() == "") {
			alert("测试用例集名称不能为空");
			return;
		}
		if ($("#method_des").val() == "") {
			alert("测试用例集描述不能为空");
			return;
		}
		var data = {};
		data.service_id = $("#serviceid1").val();
		data.method_name = $("#method_name").val();
		data.method_des = $("#method_des").val();
		var url = path + "/testmethod/add.do";
		ajaxReq(data, url, doCall, "post");
	}
	function run(id,name) {
		var data = {};
		data.sid = id;
		data.sname = name;
		var url = path + "/testmethod/run.do";
		//ajaxReq(data, url, doCall, "post");
		$.post(url, data, function(res) {
			$("#runmsg").html(res.message);
			$("#runmsgexecutionid").val(res.obj.execution_id);
			$("#runmsgexecutionid").html(res.obj.execution_id);
			$(".zhixing").modal('show');
		});
	}
	function hrefExecutionIndex(){
		var id = $("#runmsgexecutionid").val();
		window.location.href=path+"/testexecution/selectByExecutionId.do?sid="+id
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
				<h3>测试用例集</h3>
			</div>
			<div class="row" style="padding-top: 50px;">
				<div class="col-md-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<!--<div class="panel-options"> <a href="#"> <i class="linecons-cog"></i> </a> <a href="#" data-toggle="panel"> <span class="collapse-icon">–</span> <span class="expand-icon">+</span> </a> <a href="#" data-toggle="reload"> <i class="fa-rotate-right"></i> </a> <a href="#" data-toggle="remove"> × </a> </div>-->
						</div>
						
						<div class="screen">
						<form action="<%=path %>/testmethod/index.do" id="payForm" method="post">
							<ul class="shaix clearfix">
							<!--
								<li><span>服务名称：</span> <select name="initserviceselect" id="initserviceselect" onchange="selectChangeService(this)">
										<option value="">全部服务</option>
										<c:forEach items="${initServiceList }" var="item" >
											<option value="${item.service_id }" <c:if test="${param.initserviceselect == item.service_id }">selected</c:if> >${item.service_name }</option>
										</c:forEach>
								</select></li>
								
								<li><span>用例集名称：</span> <select name="initmethodselect" id="initmethodselect" onchange="selectChangeMethod(this)">
										<option value="">全部用例集</option>
										<c:forEach items="${initMethodList }" var="item">
											<option value="${item.method_id }" <c:if test="${param.initmethodselect == item.method_id }">selected</c:if> >${item.method_name }</option>
										</c:forEach>
								</select></li>
							-->	
								<li>
								<c:forEach var="item" items="${serviceInfo }" >
									<span><strong id="serviceid" style="display:none">${item.service_id }</strong></span>
									<span><strong>*模块名称：</strong><strong id="servicename">${item.service_name }</strong></span>
									<span><strong id="servicedes" style="display:none">${item.service_des }</strong></span>
								</c:forEach>
								</li>
								<li>
									<button type="button" class="addnew" onclick="addBefore2()">新增用例集</button>
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
					<h3>测试用例集-编辑</h3>
					<div class="bwarp">
							<ul>
							<li class="clearfix bgwhite">
								<div class="fp">
									<!-- <span><strong>*</strong>模块名称:</span> <select id="allService"></select>  -->
									<span><strong>*</strong>模块Id:</span><input type="text" id="serviceid1"  disabled="disabled"/>
								</div>
								<div class="fp">
									<!-- <span><strong>*</strong>模块名称:</span> <select id="allService"></select>  -->
									<span><strong>*</strong>模块名称:</span><input type="text" id="servicename1"  disabled="disabled"/>
								</div>
							</li>
							<li class="clearfix bgwhite">
								<div class="fp">
									<span><strong>*</strong>用例集名称:</span> <input type="text" id="method_name" onblur="chBlur('method_name','method_name_span','用例集名称不能为空')"/><span id="method_name_span" style="color: red;font-size:13px"></span>
								</div>
								<div class="fp">
									<span><strong>*</strong>用例集描述:</span> <input type="text" id="method_des" onblur="chBlur('method_des','method_des_span','用例集描述不能为空')"/><span id="method_des_span" style="color: red;font-size:13px"></span>
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

	<!-- 修改 -->
	<div class="modal fade edit" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel">
		<div class="modal-dialog xinzm">
			<div class="modal-content">
				<div class="xzmain">
					<h3>测试集-编辑</h3>
					<div class="bwarp">
							<ul>
							<li class="clearfix bgwhite">
								<div class="fp">
									<!-- <span><strong>*</strong>模块名称:</span> <select id="allService"></select>  -->
									<span><strong>*</strong>模块Id:</span><input type="text" id="serviceid_2"  disabled="disabled"/>
								</div>
								<div class="fp">
									<span><strong>*</strong>用例集id:</span> <input type="text"	 id="method_id_2"  disabled="disabled"/>
								</div>
								<div class="fp">
									<span><strong>*</strong>用例集名称:</span> <input type="text" id="method_name_2" disabled="disabled"/>
								</div>
							</li>
							<li class="clearfix bgwhite">
								<div class="fp">
									<span><strong>*</strong>用例集描述:</span> <input type="text" id="method_des_2" onblur="chBlur('method_des_2','method_des_span_2','服务描述不能为空')"/><span id="method_des_span_2" style="color: red;font-size:13px"></span>
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
							<span>测试用例集:<strong id="methodname4"></strong></span>
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
					<h4 class="modal-title" id="myModalLabel">信息提示</h4>
				</div>
				<div class="modal-body">
					<div class="xints">
						<h4>是否删除</h4>
						<p>
							<span>测试用例集:<strong id="methodname3"></strong></span>
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
							<strong id="runmsgexecutionid" style="display:none"></strong>
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