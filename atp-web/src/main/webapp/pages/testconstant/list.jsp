<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="table table-bordered" id="methodid" >
	<tr>
		<th>描述</th>
		<th>名称</th>
		<th>值</th>
		<th>值类型</th>
		<th>数据类型</th>
		<th>ID</th>
	</tr>
	<c:forEach var="item" items="${constantList }" >
		<tr class="">
			<td>${item.constant_des }</td>
			<td>${item.constant_name }</td>
			<td>${item.constant_value }</td>
			<td>
			<c:if test="${item.constant_value_type == '0'}">String</c:if>
			<c:if test="${item.constant_value_type == '1'}">Int</c:if>
	        </td> 
			<td>
			<c:if test="${item.constant_type == '10'}">系统常量</c:if>
			<c:if test="${item.constant_type == '11'}">系统变量</c:if>
			<c:if test="${item.constant_type == '20'}">业务常量</c:if>
			<c:if test="${item.constant_type == '21'}">业务变量</c:if>
	        </td> 
			<td>${item.constant_id }</td>
		</tr>
	</c:forEach>
</table>