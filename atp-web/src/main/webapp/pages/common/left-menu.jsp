<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="<%=path%>/js/common/common.js"></script>
<script src="<%=path%>/js/common/map.js"></script>
<script src="<%=path%>/js/common/date.js"></script>
<script src="<%=path%>/js/common/clr.js"></script>
<script src="<%=path%>/js/signout.js"></script>
<script src="<%=path%>/js/oplog.js"></script>
<script>
	var path='<%=path%>';
	function openLogout(){
		/* $(".logoutdiv").modal("show"); */
		window.location.href=path+'/login.do';
	}
	function logoutdo(){
		window.location.href=path+'/logout.do';
	}
</script>
<div class="sidebar-menu toggle-others fixed">
<div class="sidebar-menu-inner">
<header class="logo-env">	
			<!--左侧导航栏收缩 -->
			<div class="hidden-sm hidden-xs logo-expanded nomor">
				<a href="#" data-toggle="sidebar"><i class="fa-bars"></i>
				</a>
			</div>
			<div class="logo">
				<a href="#" class="logo-expanded"><img
					src="<%=path%>/img/logo.png" /> 自动化测试系统
				</a><a href="#" class="logo-collapsed" data-toggle="sidebar"><img
					src="<%=path%>/img/logo.png" /></a>
			</div>
			<div class="mobile-menu-toggle visible-xs">
				<a href="#" data-toggle="mobile-menu"><i class="fa-bars"></i>
				</a>
			</div>
			<div class="settings-icon">
				<a href="#" data-toggle="settings-pane" data-animate="true"><i
					class="linecons-cog"></i>
				</a>
			</div>
</header>
		<ul id="main-menu" class="main-menu">
			<li class="userpic"><a href="javascript:void(0)" onclick="openLogout()"><i class="linecons-cog"><img src="<%=path%>/img/user-tpic.png" /></i><span>${sessionUser.userBasicVo.userName }</span><span class="ext">退出登录</span></a>
			</li>
			
			<!-- <li><a href="#"><i class="linecons-cog"><img src="<%=path%>/img/icon01.png" /></i><span class="title">接口测试</span></a>  -->
			<li><a href="#"><i class="linecons-cog"><img src="<%=path%>/img/icon01.png" /></i><span class="title">接口测试</span></a>
				<ul>
					<li><a href="<%=path%>/testservice/index.do"><span class="title">测试服务</span></a></li>
					<li><a href="<%=path%>/testmethod/index.do"><span class="title">测试用例集</span></a></li>
					<li><a href="<%=path%>/testsuite/index.do"><span class="title">测试用例</span></a></li>
					<li><a href="<%=path%>/testcase/index.do"><span class="title">用例步骤</span></a></li>
					<li><a href="<%=path%>/jsoneditor/index.do"><span class="title">josn编辑页面</span></a></li>
				</ul>
			</li>
			
			<li><a href="#"><i class="linecons-desktop"><img src="<%=path%>/img/icon06.png" /></i><span class="title">测试数据</span></a>
				<ul>
					<li><a href="<%=path%>/testconstant/index.do"><span class="title">常量变量</span></a></li>
				</ul>
			</li>
			
			<!--  
			<li><a href="#"><i class="linecons-desktop"><img src="<%=path%>/img/icon10.png" /></i><span class="title">初始化服务</span></a>
				<ul>
					<li><a href="<%=path%>/initService/index.do"><span class="title">初始服务</span></a></li>
					<li><a href="<%=path%>/configmethod/index.do"><span class="title">初始入参</span></a></li>
					<li><a href="<%=path%>/configway/index.do"><span class="title">初始属性</span></a></li>  
				</ul>
			</li>
			<li><a href="#"><i class="linecons-note"><img src="<%=path%>/img/icon06.png" /></i><span class="title">系统管理</span></a>
				<ul>
				<li class="active"> <a href="<%=path%>/toPwd.do"> <span class="title">修改密码</span> </a> </li>
				</ul>
			</li>
			-->
			<li><a href="#"><i class="linecons-desktop"><img src="<%=path%>/img/icon07.png" /></i><span class="title">测试报告</span></a>
				<ul>
					<li><a href="<%=path%>/testexecution/index.do"><span class="title">用例执行报告</span></a></li>
				</ul>
			</li>
		</ul>
	</div>
</div>