<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>首页</title>
	
  </head>
  
  <body>
   	<a href="<%=basePath%>user/getAllUser">进入用户管理页</a></h5>
   	<a href="<%=basePath%>user/getAllUser">进入自动化系统</a></h5>
   	<a href="<%=basePath%>testSuite/towelcome.do">进入自动化系统1</a></h5>
   	<a href='pages/welcome.jsp'>b文件下的文件</a>
  </body>
</html>
