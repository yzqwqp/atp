<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<table class="table table-bordered" id="methodid" >
	<tr>
		<th>执行Id</th>
		<th>执行类型</th>
		<th>执行描述</th>
		<th>执行类型ID</th>
		<th>执行开始时间</th>
		<th>执行结束时间</th>
		<th>执行耗时（s）</th>
		<th>执行总数</th>
		<th>执行成功数</th>
		<th>执行失败数</th>
		<th>未执行数</th>
		<th>执行记录</th>
		<th>操作</th>
	</tr>
	<c:forEach var="item" items="${executionInfoList }" >
		<tr class="">
			<td>${item.execution_id }</td>
			<td>${item.execution_type == '1' ? '执行测试用例' : item.execution_type == '2' ? '执行测试用例集' : '执行测试模块' }</td>
			<td>${item.execution_type_name }</td>
			<td>${item.execution_type_value }</td>
			<td><fmt:formatDate value="${item.execution_start_time }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td><fmt:formatDate value="${item.execution_end_time }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td>${item.execution_time }秒</td>
			<td>${item.total_num }</td>
			<td>${item.true_num }</td>
			<td> <a href="javascript:hrefFailureResultList('${item.execution_id}')"> ${item.failure_num }</a> </td>
			<td> <a href="javascript:hrefUnrunResultList('${item.execution_id}')"> ${item.unrun_num }</a> </td>
			<td>${item.execution_detail }</td>
			<td>
				<a href="javascript:hrefResultList('${item.execution_id}')">查看详情</a>
			</td>
		</tr>
	</c:forEach>
</table>