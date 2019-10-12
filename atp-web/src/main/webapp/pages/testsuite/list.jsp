<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="table table-bordered" id="methodid" >
	<tr>
		<th>测试用例Id</th>
		<th>用例描述</th>
		<th>是否运行</th>
		<th>操作</th>
	</tr>
	<c:forEach var="item" items="${suiteList }" >
		<tr class="">
			<td>${item.suite_id }</td>
			<td>${item.suite_des }</td>
			<td>${item.is_run == '1' ? 'YES' : 'NO' }</td>
			<td>
				<a href="javascript:edit('${item.suite_id}')">编辑</a>
				<a href="javascript:del('${item.suite_id }','${item.suite_des }')">删除</a>
				<a href="javascript:hrefCaseIndex('${item.suite_id}')">查看步骤</a>
				<a href="javascript:run('${item.suite_id}','${item.suite_des }')">执行</a>
			</td>
		</tr>
	</c:forEach>
</table>