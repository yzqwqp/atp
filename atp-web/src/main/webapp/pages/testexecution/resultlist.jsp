<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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