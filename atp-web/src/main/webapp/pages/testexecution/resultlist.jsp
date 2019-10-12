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
</script>
</head>
<body class="page-body">
	<!-- 查看结果 -->
	<div class="page-container">
		<!--progress-->
		<%@include file="../common/left-menu.jsp"%>
		<div class="xinz" tabindex="-1" 
			aria-labelledby="mySmallModalLabel">
			<div class="xinzm">
				<div class="modal-content">
					<div class="xzmain">
						<h3>查看结果</h3>
						<div class="bwarp">
							<div class="table-responsive" id="table_list2">
								<table class="table table-bordered" id="resultId" >
									<tr>
											<th>执行明细Id</th>
											<th>用例ID</th>
											<th>用例步骤ID</th>
											<th>用例步骤描述</th>
											<th>请求地址</th>
											<th>请求json</th>
											<th>响应报文</th>
											<th>断言类型</th>
											<th>预期断言值</th>
											<th>实际值</th>
											<th>http状态</th>
											<th>断言状态</th>
											<th>http异常</th>
											<th>断言异常</th>
										</tr>
										<c:forEach var="item" items="${resultList}" >
											<tr class="">
												<td>${item.result_id }</td>
												<td>${item.suite_id }</td>
												<td>${item.case_id }</td>
												<td>${item.case_des }</td>
												<td>${item.method_address }</td>
												<td>${item.case_data }</td>
												<td>${item.response_data }</td>
												<td>${item.case_assert_type }</td>
												<td>${item.case_assert_value }</td>
												<td>${item.response_assert_value }</td>
												<td>${item.http_status }</td>
												<td>${item.assert_status }</td>
												<td>${item.http_error }</td>
												<td>${item.assert_error }</td>
											</tr>
										</c:forEach>
									</table>
							</div>
							<%@include file="../common/page.jsp" %>
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
