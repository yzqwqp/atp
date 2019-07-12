<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="table table-bordered" id="methodid" >
	<tr>
		<th>方法id</th>
		<th>方法名称</th>
		<th>方法描述</th>
		<th>操作</th>
	</tr>
	<c:forEach var="item" items="${methodList }" >
		<tr class="">
			<td>${item.method_id }</td>
			<td>${item.method_name }</td>
			<td>${item.method_des }</td>
			<td>
				<a href="javascript:edit('${item.method_id}')">编辑</a>
				<a href="javascript:del('${item.method_id }','${item.method_name }')">删除</a>
				<a href="javascript:hrefCaseIndex('${item.method_id}')">用例</a>
			</td>
		</tr>
	</c:forEach>
</table>