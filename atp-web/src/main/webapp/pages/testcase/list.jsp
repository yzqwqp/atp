<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="table table-bordered" id="methodid" >
	<tr>
		<th>用例Id</th>
		<th>用例名称</th>
		<th>步骤顺序</th>
		<th>步骤描述</th>
		<th>请求地址</th>
		<th>数据（json格式）</th>
		<th>断言类型</th>
		<th>断言的值</th>
		<th>执行前处理</th>
		<th>执行后处理</th>
		<th>操作步骤Id</th>
		<th style="display:none">操作步骤Id</th>
		<th>操作</th>
	</tr>
	<c:forEach var="item" items="${caseList }" >
		<tr class="">
			<td>${item.suite_id }</td>
			<td>${item.suite_des }</td>
			<td>${item.case_run_num }</td>
			<td>${item.case_des }</td>
			<td>${item.method_address }</td>
			<td>${item.case_data }</td>
			<td>${item.case_assert_type == '0' ? '不断言' : '断言返回结果'  }</td>
			<td>${item.case_assert_value }</td>
			<td>${item.before_run }</td>
			<td>${item.after_run == '1' ? '处理token' : '不处理'  }</td>
			<td>${item.case_id }</td>
			<td style="display:none">${item.case_id }</td>
			<td>
				<a href="javascript:edit('${item.case_id}')">编辑</a>
				<a href="javascript:del('${item.case_id }','${item.case_des }')">删除</a>
			</td>
		</tr>
	</c:forEach>
</table>