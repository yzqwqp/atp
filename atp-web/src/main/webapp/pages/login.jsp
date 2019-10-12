<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../pages/common/title.jsp"%>
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
function login(){
	var id = $("#runmsgexecutionid").val();
	window.location.href=path+"/testexecution/selectByExecutionId.do?sid="+id
}


</script>
<style>
.userName input, .pw input {
	min-width: 200px;
	height: 30px;
	border: 1px solid #999;
	border-radius: 3px;
	margin-bottom: 20px;
	padding-left: 4px;
}

.login {
	width: 90px;
	height: 30px;
	background-color: #fd9727;
	color: #fff;
	font-size: 14px;
	line-height: 30px;
	text-align: center;
	border-radius: 4px;
	border: none;
	outline: none;
	margin: 0 auto;
	margin-top: 20px;
	margin-left: 90px;
}

.loginCont {
	width: 600px;
	margin: 100px auto;
}

.loginCont lable {
	min-width: 90px;
	text-align: left;
	font-size: 14px;
	color: #333;
	display: inline-block;
	vertical-align: middle;
}
</style>
</head>
<body class="page-body">
	<input type="hidden" value="${params }" id="skipParam">
	<div class="page-container">
		<!--progress-->
		<%-- <%@include file="../common/left-menu.jsp"%> --%>
		<div class="main-content">
			<div class="topv">
				<h3>用户登录</h3>
			</div>
			<div class="loginCont" style="padding-top: 50px;">
				<div class="userName">
					<lable>用户名：</lable>
					<input type="text" id="userName" placeholder="请输入用户名" />
				</div>
				<div class="pw">
					<lable>密码：</lable>
					<input type="password" id="pwd" placeholder="请输入密码" />
				</div>
				<div class="">
					<button type="button" class="login" onclick="test123()">登录</button>
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
		<script type="text/javascript">
		function test123(){
			
			debugger;
			
			var userName = $("#userName").val();
			var pwd = $("#pwd").val();
			 $.ajax({
		        type:'post',
		        url:'<%=basePath%>/testservice1/login.do',
		        dataType:'text',
		        async:false,
		        data:{"userName":userName, "pwd":pwd},
		        success: function(result){
		            if(result && result == "登录成功")
		           	 window.location.href = "http://www.baidu.com";
		           	 else alert(result);
		            return result;
		        }
		     
			 });
		}
	</script>
</body>
</html>