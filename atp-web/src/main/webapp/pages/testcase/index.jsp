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
		data.caseId=id;
		var url=path+"/testcase/selectByCaseId.do";
		// ajaxReq(data, url, showSingle, "post");
		$.post(url, data, function(res) {
			showSingle(res);
			$(".edit").modal("show");
		});
	}
	function showSingle(data){
		
		$("#suite_id2").val(data.suite_id);
		$("#suite_des2").val(data.suite_des);
		
		$("#case_run_num2").val(data.case_run_num);

		$("#case_id2").val(data.case_id);
		$("#case_des2").val(data.case_des);
		$("#case_type2").val(data.case_type);
		
		var a = data.case_type;
		if(a==0){
			$("#method_address_type_id2").hide();
			$("#method_address_id2").show();
			$("#method_address2").val(data.method_address);
		}else{
			$("#method_address_type_id2").show();
			$("#method_address_id2").hide();
			$("#method_address_type2").val(data.method_address);
		}
		
		$("#case_data2").val(data.case_data);
		
		$("#case_assert_tpye2").val(data.case_assert_type);
		$("#case_assert_value2").val(data.case_assert_value);
		
		$("#before_run_type2").val(data.before_run_type);
		$("#before_run2").val(data.before_run);
		
		$("#after_run_type2").val(data.after_run_type);
		$("#after_run2").val(data.after_run);
		
	}
	function update(){
		if ($("#suite_id2").val() == "") {
			alert("用例ID不能为空");
			return;
		}
		if ($("#case_run_num2").val() == "") {
			alert("执行顺序不能为空");
			return;
		}
		if ($("#case_des2").val() == "") {
			alert("步骤描述不能为空");
			return;
		}
		if($("#case_type2").val() == 0){
			if ($("#method_address2").val() == "") {
				alert("请求地址不能为空");
				return;
			}
		}
		if ($("#case_data2").val() == "") {
			alert("json数据不能为空");
			return;
		}
		if ($("#case_assert_tpye2").val() == "") {
			alert("断言类型不能为空");
			return;
		}
		if ($("#before_run_type2").val() == "") {
			alert("执行前处理类型不能为空，请设置为不执行");
			return;
		}
		if ($("#case_assert_value2").val() == "") {
			alert("断言值不能为空");
			return;
		}
		if ($("#after_run_type2").val() == "") {
			alert("执行后处理类型不能为空，请设置为不执行");
			return;
		}
		var data = {};
		var a = $("#case_type2").val();
		data.suite_id = $("#suite_id2").val();
		data.case_id = $("#case_id2").val();
		data.case_type = a;
		if(a==0){
			data.method_address = $("#method_address2").val();
		} else {
			data.method_address = $("#method_address_type2").val();
		}
		data.case_des = $("#case_des2").val();
		data.case_des = $("#case_type2").val();
		data.case_data = $("#case_data2").val();
		data.case_assert_type = $("#case_assert_tpye2").val();
		data.case_assert_value = $("#case_assert_value2").val();
		data.before_run_type = $("#before_run_type2").val();
		data.before_run = $("#before_run2").val();
		data.after_run_type = $("#after_run_type2").val();
		data.after_run = $("#after_run2").val();
		data.case_run_num = $("#case_run_num2").val();
		var url = path + "/testcase/updateById.do";
		// ajaxReq(data, url, doCall, "post");
		$.post(url, data, function(res) {
			$("#msgshow").html(res.obj);
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
			var url = path + "/testcase/deleteById.do";
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
		//var data={};
		//data.sid=$("#initmethodselect").val();
		//var url=path+"/testmethod/selectMethodId.do";
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
		//var value=$(event).val();
		//var url=path+"/testcase/createdMethod.do";
		//var data={sid: value};
		// ajaxReq(data, url, selectChangeServiceCall, "post");
		query();
	}
	function selectChangeSuite(event){
		//var value=$(event).val();
		//var url=path+"/testcase/createdMethod.do";
		//var data={sid: value};
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
		$("#msgshow").html(result.obj);
		$(".xinxi").modal('show');
	}
	function skipIndex(){
		if (typeof($("#suite_id").html()) == "undefined"){ 
			window.location.href=path+"/testcase/index.do"
		} else {
			var id = $("#suite_id").html();
			window.location.href=path+"/testcase/selectBySuiteIdToCase.do?sid="+id
		}
	}
	function addBefore() {
		$(".xinz").modal("show");
		/*var a = $("#initserviceselect").val();
		var b = $("#initserviceselect").find("option:selected").html();
		$("#serviceselect option").val(a);
		$("#serviceselect option").html(b);
		var a = $("#initmethodselect").val();
		var b = $("#initmethodselect").find("option:selected").html();
		$("#methodselect option").val(a);
		$("#methodselect option").html(b);
		var a = $("#initsuiteselect").val();
		var b = $("#initsuiteselect").find("option:selected").html();
		$("#suiteselect option").val(a);
		$("#suiteselect option").html(b);
		*/
		
		var a = $("#suite_id").html();
		$("#suiteid").val(a);
		$("#suiteid").html(a);
	}
	function add() {
		if ($("#suiteid").val() == "") {
			alert("用例ID不能为空");
			return;
		}
		if ($("#case_run_num").val() == "") {
			alert("执行顺序不能为空");
			return;
		}
		if ($("#case_des").val() == "") {
			alert("用例描述不能为空");
			return;
		}
		if($("#case_type").val() == 0){
			if ($("#method_address").val() == "") {
				alert("请求地址不能为空");
				return;
			}
		}
		if ($("#case_data").val() == "") {
			alert("json数据不能为空");
			return;
		}
		if ($("#case_assert_tpye").val() == "") {
			alert("断言类型不能为空");
			return;
		}
		if ($("#case_assert_value").val() == "") {
			alert("断言值不能为空");
			return;
		}
		if ($("#before_run_type").val() == "") {
			alert("执行前处理类型不能为空，请设置为不执行");
			return;
		}
		if ($("#after_run_type").val() == "") {
			alert("执行后处理类型不能为空，请设置为不执行");
			return;
		}
		var data = {};
		var a = $("#case_type").val();
		data.suite_id = $("#suiteid").val();
		data.case_run_num = $("#case_run_num").val();
		data.case_des = $("#case_des").val();
		data.case_type = a;
		if(a==0){
			data.method_address = $("#method_address").val();
		} else {
			data.method_address = $("#method_address_type").val();
		}
		data.case_data = $("#case_data").val();
		data.case_assert_type = $("#case_assert_tpye").val();
		data.case_assert_value = $("#case_assert_value").val();
		data.before_run_type = $("#before_run_type").val();
		data.before_run = $("#before_run").val();
		data.after_run_type = $("#after_run_type").val();
		data.after_run = $("#after_run").val();
		var url = path + "/testcase/add.do";
		ajaxReq(data, url, doCall, "post");
	}
	function run(id) {
		var data = {};
		data.sid = id;
		var url = path + "/testcase/run.do";
		//ajaxReq(data, url, doCall, "post");
		$.post(url, data, function(res) {
			$("#msgshow").html(res.obj);
			$(".xinxi").modal('show');
		});
	}
	function changeMethodAddress(event){
		var a = $("#case_type").val();
		if(a==1){
			$("#method_address_type_id").show();
			$("#method_address_id").hide();
		}else {
			$("#method_address_type_id").hide();
			$("#method_address_id").show();
		}
	}
	function changeMethodAddress2(event){
		var a = $("#case_type2").val();
		if(a==1){
			$("#method_address_type_id2").show();
			$("#method_address_id2").hide();
		}else {
			$("#method_address_type_id2").hide();
			$("#method_address_id2").show();
		}
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
				<h3>用例步骤</h3>
			</div>
			<div class="row" style="padding-top: 50px;">
				<div class="col-md-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<!--<div class="panel-options"> <a href="#"> <i class="linecons-cog"></i> </a> <a href="#" data-toggle="panel"> <span class="collapse-icon">–</span> <span class="expand-icon">+</span> </a> <a href="#" data-toggle="reload"> <i class="fa-rotate-right"></i> </a> <a href="#" data-toggle="remove"> × </a> </div>-->
						</div>
						
						
						<div class="screen">
						<form action="<%=path %>/testcase/index.do" id="payForm" method="post">
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
								<li><span>测试用例：</span> <select name="initsuiteselect" id="initsuiteselect" onchange="selectChangeSuite(this)">
										<c:forEach items="${initSuiteList }" var="item">
											<option value="${item.suite_id }" <c:if test="${param.initsuiteselect == item.suite_id }">selected</c:if> >${item.suite_id }-${item.suite_des }</option>
										</c:forEach>
								</select></li>
								<li><a href="javascript:void(0)" class="sxbtn" onclick="query()"> <span
										class="glyphicon glyphicon-search"></span> 筛选
								</a></li>
								 -->
								<li>
								<c:forEach var="item" items="${suiteInfo }" >
									<span><strong id="suite_id"  style="display:none">${item.suite_id } </strong></span>
									<span><strong>*用例：</strong><strong id="suite_des" >${item.suite_des } </strong></span>
								</c:forEach>
								</li>
								<li>
									<button type="button" class="addnew" onclick="addBefore()">新增用例步骤</button>
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
					<h3>用例步骤-新增</h3>
					<div class="bwarp">
							<ul>
							<!-- 
							<li class="clearfix bgwhite">
								<div class="fp">
									<span><strong>*</strong>服务名称:</span> <select id="serviceselect" ><option value=""></option></select>
								</div>
								<div class="fp">
									<span><strong>*</strong>测试用例集:</span> <select id="methodselect" ><option value=""></option></select>
								</div>
							</li>
							 -->
							<li class="clearfix bgwhite">
								<div class="fp">
									<!-- <span><strong>*</strong>测试用例:</span> <select id="suiteselect" ><option value=""></option></select> -->
									<span><strong>*</strong>用例Id:</span><input type="text" id="suiteid"  disabled="disabled" />
								</div>
								<div class="fp">
									<span><strong>*</strong>执行顺序:</span> 
									<select id="case_run_num" >
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
										<option value="6">6</option>
										<option value="7">7</option>
										<option value="8">8</option>
										<option value="9">9</option>
									</select>
								</div>
							</li>
							<li class="clearfix bgwhite">
								<div class="fp">
									<span><strong>*</strong>步骤描述:</span> <input type="text" id="case_des" onblur="chBlur('case_des','case_des_span','不能为空')"/><span id="case_des_span" style="color: red;font-size:13px"></span>
								</div> 
								<div class="fp">
									<span><strong>*</strong>步骤类型:</span>
									<select id="case_type" onchange="changeMethodAddress(this)">
										<option value="0">正常步骤</option>
										<option value="1">SQL步骤</option>
									</select>
								</div>
							</li>
							<li class="clearfix bgwhite">
								<div class="fp" id="method_address_id">
									<span><strong>*</strong>请求地址:</span> <input type="text" id="method_address" onblur="chBlur('method_address','method_address_span','不能为空')"/><span id="method_address_span" style="color: red;font-size:13px"></span>
								</div>
								<div class="fp" id="method_address_type_id" style="display:none">
									<span><strong>*</strong>sql类型:</span>
									<select id="method_address_type">
										<option value="0">update</option>
										<option value="1">delete</option>
									</select>
								</div>
								<div class="fp">
									<span><strong>*</strong>数据json:</span><input type="text" id="case_data" onblur="chBlur('case_data','case_data_span','不能为空')"/><span id="case_data_span" style="color: red;font-size:13px"></span>
								</div>						
							</li>
							<li class="clearfix bgwhite">
								<div class="fp">
									<span><strong>*</strong>断言类型:</span>
									<select id="case_assert_tpye" >
										<option value="0">不断言</option>
										<option value="1">断言返回结果</option>
									</select>
								</div>
								<div class="fp">
									<span><strong>*</strong>断言值:</span> <input type="text" id="case_assert_value" onblur="chBlur('case_assert_value','case_assert_value_span','不能为空')"/><span id="case_assert_value_span" style="color: red;font-size:13px"></span>
								</div>
							</li>
							<li class="clearfix bgwhite">
								<div class="fp">
									<span><strong>*</strong>执行前处理类型:</span>
									<select id="before_run_type" >
										<option value="0">不处理</option>
										<option value="1">执行其他步骤</option>
										<option value="2">替换业务常量或变量</option>
										<option value="3">替换SQL变量</option>
									</select>
								</div>
								<div class="fp">
									<span>执行前处理:</span> <input type="text" id="before_run"/><span id="before_run_span" style="color: red;font-size:13px"></span>
								</div>
							</li>
							<li class="clearfix bgwhite">
								<div class="fp">
									<span><strong>*</strong>执行后处理类型:</span>
									<select id="after_run_type" >
										<option value="0">不处理</option>
										<option value="1">处理token</option>
										<option value="2">更新业务变量</option>
									</select>
								</div>
								<div class="fp">
									<span>执行后处理:</span> <input type="text" id="after_run"/><span id="after_run_span" style="color: red;font-size:13px"></span>
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
									<span><strong>*</strong>用例ID:</span> <input type="text" id="suite_id2" disabled="disabled"/>
								</div>
								<div class="fp">
									<span><strong>*</strong>步骤顺序:</span> 
									<select id="case_run_num2" >
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
										<option value="6">6</option>
										<option value="7">7</option>
										<option value="8">8</option>
										<option value="9">9</option>
									</select>
									<input type="hidden" id="case_id2" disabled="disabled"/>
								</div>
							</li>
							<li class="clearfix bgwhite">
								<div class="fp">
									<span><strong>*</strong>步骤描述:</span> <input type="text" id="case_des2" onblur="chBlur('case_des2','case_des2_span','用例描述不能为空')"/><span id="case_des2_span" style="color: red;font-size:13px"></span>
								</div> 
								<div class="fp">
									<span><strong>*</strong>步骤类型:</span>
									<select id="case_type2" onchange="changeMethodAddress2(this)">
										<option value="0">正常步骤</option>
										<option value="1">SQL步骤</option>
									</select>
								</div>			
							</li>
							<li class="clearfix bgwhite">
								<div class="fp" id="method_address_id2">
									<span><strong>*</strong>请求地址:</span> <input type="text" id="method_address2" onblur="chBlur('method_address2','method_address2_span','方法地址不能为空')"/><span id="method_address2_span" style="color: red;font-size:13px"></span>
								</div>
								<div class="fp" id="method_address_type_id2">
									<span><strong>*</strong>sql类型:</span>
									<select id="method_address_type2">
										<option value="0">update</option>
										<option value="1">delete</option>
									</select>
								</div>
								<div class="fp">
									<span><strong>*</strong>数据json:</span><input type="text" id="case_data2" onblur="chBlur('case_data2','case_data2_span','用例数据不能为空')"/><span id="case_data2_span" style="color: red;font-size:13px"></span>
								</div>						
							</li>
							<li class="clearfix bgwhite">
								<div class="fp">
									<span><strong>*</strong>断言类型:</span>
									<select id="case_assert_tpye2" >
										<option value="0">不断言</option>
										<option value="1">断言返回结果</option>
									</select>
								</div>
								<div class="fp">
									<span><strong>*</strong>断言值:</span> <input type="text" id="case_assert_value2" onblur="chBlur('case_assert_value2','case_assert_value2_span','方法名称不能为空')"/><span id="case_assert_value2_span" style="color: red;font-size:13px"></span>
								</div>
							</li>
							<li class="clearfix bgwhite">
								<div class="fp">
									<span><strong>*</strong>执行前处理类型:</span>
									<select id="before_run_type2" >
										<option value="0">不处理</option>
										<option value="1">执行其他步骤</option>
										<option value="2">替换业务常量或变量</option>
										<option value="3">替换SQL变量</option>
									</select>
								</div>
								<div class="fp">
									<span>执行前处理:</span> <input type="text" id="before_run2"/><span id="before_run2_span" style="color: red;font-size:13px"></span>
								</div>
							</li>
							<li class="clearfix bgwhite">
								<div class="fp">
									<span><strong>*</strong>执行后处理:</span>
									<select id="after_run_type2" >
										<option value="0">不处理</option>
										<option value="1">处理token</option>
										<option value="2">更新业务变量</option>
									</select>
								</div>
								<div class="fp">
									<span>执行前处理:</span> <input type="text" id="after_run2"/><span id="after_run2_span" style="color: red;font-size:13px"></span>
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
							<span>测试用例:<strong id="msgshow"></strong></span>
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