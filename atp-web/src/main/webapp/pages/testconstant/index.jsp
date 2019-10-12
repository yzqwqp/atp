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
	function addBefore() {
		$(".xinz").modal("show");
	}
	function add() {
		if ($("#constant_name").val() == "") {
			alert("名称不能为空");
			return;
		}
		if ($("#constant_value").val() == "") {
			alert("值不能为空");
			return;
		}
		if ($("#constant_value_type").val() == "") {
			alert("值类型不能为空");
			return;
		}
		if ($("#constant_des").val() == "") {
			alert("描述不能为空");
			return;
		}
		if ($("#constant_type").val() == "") {
			alert("类型不能为空");
			return;
		}
		
		var data = {};
		data.constant_name = $("#constant_name").val();
		data.constant_value = $("#constant_value").val();
		data.constant_value_type = $("#constant_value_type").val();
		data.constant_des = $("#constant_des").val();
		data.constant_type = $("#constant_type").val();
		var url = path + "/testconstant/add.do";
		ajaxReq(data, url, doCall, "post");
	}
	function doCall(result){
		$("#methodname4").html(result.obj);
		$(".xinxi").modal('show');
	}
	function skipIndex(){
		window.location.href=path+"/testconstant/index.do"
	}
	function chBlur(a, b, c) {
		if ($("#" + a).val() == "") {
			$("#" + b).html(c)
			return false;
		}
		$("#" + b).html("")
		return true;
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
				<h3>测试数据</h3>
			</div>
			<div class="row" style="padding-top: 50px;">
				<div class="col-md-12">
					<div class="panel panel-default">
						<div class="panel-heading">
					</div>
					<div class="screen">
						<ul class="shaix clearfix">
							<li>
								<button type="button" class="addnew" onclick="addBefore()">新增</button>
							</li>
						</ul>
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
					<h3>新增</h3>
					<div class="bwarp">
						<ul>
							<li class="clearfix bgwhite">
								<div class="fp">
									<span><strong>*</strong>数据类型:</span>
									<select id="constant_type" >
										<option value="20">业务常量</option>
										<option value="21">业务变量</option>
										<option value="10">系统常量</option>
										<option value="11">系统变量</option>
									</select>
								</div>
							</li>
							<li class="clearfix bgwhite">
								<div class="fp">
									<span><strong>*</strong>名称:</span> <input type="text" id="constant_name" onblur="chBlur('constant_name','constant_name_span','常量名称不能为空')"/><span id="constant_name_span" style="color: red;font-size:13px"></span>
								</div>
								<div class="fp">
									<span><strong>*</strong>值:</span> <input type="text" id="constant_value" onblur="chBlur('constant_value','constant_value_span','常量值不能为空')"/><span id="constant_value_span" style="color: red;font-size:13px"></span>
								</div>
							</li>
							<li class="clearfix bgwhite">
								<div class="fp">
									<span><strong>*</strong>描述:</span> <input type="text" id="constant_des" onblur="chBlur('constant_des','constant_des_span','常量描述不能为空')"/><span id="constant_des_span" style="color: red;font-size:13px"></span>
								</div>				
								<div class="fp">
									<span><strong>*</strong>值类型:</span>
									<select id="constant_value_type" >
										<option value="0">String</option>
										<option value="1">Int</option>
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
							<span>常量:<strong id="methodname4"></strong></span>
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