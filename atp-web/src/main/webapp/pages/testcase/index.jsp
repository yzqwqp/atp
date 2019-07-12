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
		var url=path+"/testcase/selectById.do";
		// ajaxReq(data, url, showSingle, "post");
		$.post(url, data, function(res) {
			showSingle(res);
			$(".edit").modal("show");
		});
	}
	function showSingle(data){
		$("#case_id2").val(data.case_id);
		$("#case_des2").val(data.case_des);
		$("#case_data2").val(data.case_data);
		$("#case_assert_type2").val(data.case_assert_type);
		$("#case_assert_value2").val(data.case_assert_value);
	}
	function update(){
		var data = {};
		data.case_id = $("#case_id2").val();
		data.case_des = $("#case_des2").val();
		data.case_data = $("#case_data2").val();
		data.case_assert_type = $("#case_assert_type2").val();
		data.case_assert_value = $("#case_assert_value2").val();
		var url = path + "/testcase/updateById.do";
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
			var url = path + "/testcase/deleteById.do";
			ajaxReq(data, url, doCall, "post");
		});
	}
	function selectChangeService(event){
		var value=$(event).val();
		var url=path+"/testcase/createdMethod.do";
		var data={sid:value};
		ajaxReq(data, url, selectChangeServiceCall, "post");
	}
	function selectChangeServiceCall(result){
		var data = result.obj;
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
		var url=path+"/testcase/selectMethodId.do";
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
		var url=path+"/testcase/createdMethod.do";
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
	function skipIndex(){
		window.location.href=path+"/testcase/index.do"
	}
	function addBefore() {
		$(".xinz").modal("show");
		var a = $("#initmethodselect").val();
		var b = $("#initmethodselect").find("option:selected").html();
		$("#methodselect option").val(a);
		$("#methodselect option").html(b);
	}
	function add() {
		var data = {};
		data.method_id = $("#methodselect").val();
		data.case_des = $("#case_des").val();
		data.case_data = $("#case_data").val();
		data.case_assert_type = $("#case_assert_tpye").val();
		data.case_assert_value = $("#case_assert_value").val();
		var url = path + "/testcase/add.do";
		ajaxReq(data, url, doCall, "post");
	}
	function run(id) {
		var data = {};
		data.sid = id;
		var url = path + "/testcase/run.do";
		//ajaxReq(data, url, doCall, "post");
		$.post(url, data, function(res) {
			$("#methodname4").html(res.obj);
			$(".xinxi").modal('show');
		});
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
						<form action="<%=path %>/testcase/index.do" id="payForm" method="post">
							<ul class="shaix clearfix">
								<li><span>服务名称：</span> <select name="initserviceselect" id="initserviceselect" onchange="selectChangeService(this)">
										<option value="">全部服务</option>
										<c:forEach items="${initServiceList }" var="item" >
											<option value="${item.service_id }" <c:if test="${param.initserviceselect == item.service_id }">selected</c:if> >${item.service_name }</option>
										</c:forEach>
								</select></li>
								<li><span>方法名称：</span> <select name="initmethodselect" id="initmethodselect" onchange="selectChangeMethod(this)">
										<option value="">全部方法</option>
										<c:forEach items="${initMethodList }" var="item">
											<option value="${item.method_id }" <c:if test="${param.initmethodselect == item.method_id }">selected</c:if> >${item.method_name }</option>
										</c:forEach>
								</select></li>
								<li><a href="javascript:void(0)" class="sxbtn" onclick="query()"> <span
										class="glyphicon glyphicon-search"></span> 筛选
								</a></li>
								<li>
									<button type="button" class="addnew" onclick="addBefore()">新增方法</button>
									<!-- <button type="button" class="addnew" style="font-size:14px;dipslay:inline;" onclick="addBefore()">新增方法</button>  -->
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
					<h3>测试用例-新增</h3>
					<div class="bwarp">
							<ul>
							<li class="clearfix bgwhite">
								<div class="fp">
									<span><strong>*</strong>方法名称:</span> <select id="methodselect" ><option value=""></option></select>
								</div>
								<div class="fp">
									<span><strong>*</strong>用例描述:</span> <input type="text" id="case_des" onblur="chBlur('case_des','case_des','方法名称不能为空')"/><span id="case_des_span" style="color: red;font-size:13px"></span>
								</div>
							</li>
							<li class="clearfix bgwhite">
								<div class="fp">
									<span><strong>*</strong>用例数据json:</span><input type="text" id="case_data" onblur="chBlur('case_data','case_data','方法描述不能为空')"/><span id="case_data_span" style="color: red;font-size:13px"></span>
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
									<span><strong>*</strong>断言值:</span> <input type="text" id="case_assert_value" onblur="chBlur('case_assert_value','case_assert_value','方法名称不能为空')"/><span id="case_assert_value_span" style="color: red;font-size:13px"></span>
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
									<span><strong>*</strong>用例ID:</span> <input type="text" id="case_id2" disabled="disabled"/>
								</div>
								<div class="fp">
									<span><strong>*</strong>用例描述:</span> <input type="text" id="case_des2" onblur="chBlur('case_des2','case_des2','用例描述不能为空')"/><span id="case_des_span2" style="color: red;font-size:13px"></span>
								</div>
							</li>
							<li class="clearfix bgwhite">
								<div class="fp">
									<span><strong>*</strong>用例数据json:</span><input type="text" id="case_data2" onblur="chBlur('case_data2','case_data2','用例数据json不能为空')"/><span id="case_data_span2" style="color: red;font-size:13px"></span>
								</div>						
							</li>
							<li class="clearfix bgwhite">
								<div class="fp">
									<span><strong>*</strong>断言类型:</span>
									<select id="case_assert_type2" >
										<option value="0">不断言</option>
										<option value="1">断言返回结果</option>
									</select>
								</div>
								<div class="fp">
									<span><strong>*</strong>断言值:</span> <input type="text" id="case_assert_value2" onblur="chBlur('case_assert_value2','case_assert_value2','方法名称不能为空')"/><span id="case_assert_value_span2" style="color: red;font-size:13px"></span>
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
							<span>测试用例:<strong id="methodname4"></strong></span>
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