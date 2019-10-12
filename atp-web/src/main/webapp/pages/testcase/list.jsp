<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="table table-bordered" id="methodid" >
	<tr>
		<th>步骤顺序</th>
		<th>步骤Id</th>
		<th>步骤类型</th>
		<th>步骤描述</th>
		<th>请求地址</th>
		<th>数据（json格式）</th>
		<th>断言类型</th>
		<th>断言的值</th>
		<th>执行前处理类型</th>
		<th>执行前处理</th>
		<th>执行后处理类型</th>
		<th>执行后处理</th>
		<th style="display:none">操作步骤Id</th>
		<th>操作</th>
	</tr>
	<c:forEach var="item" items="${caseList }" >
		<tr class="">
			<td>${item.case_run_num }</td>
			<td>${item.case_id }</td>
			<td>
			<c:if test="${item.case_type == '0'}">正常步骤</c:if>
			<c:if test="${item.case_type == '1'}">SQL步骤</c:if>
	        </td>
			<td>${item.case_des }</td>
			<td>
			<c:if test="${item.case_type == '0'}">${item.method_address }</c:if>
			<c:if test="${item.case_type == '1' && item.method_address == '0'}">update</c:if>
			<c:if test="${item.case_type == '1' && item.method_address == '1'}">delete</c:if>
	        </td> 
			<td>${item.case_data }</td>
			<td>${item.case_assert_type == '0' ? '不断言' : '断言返回结果'  }</td>
			<td>${item.case_assert_value }</td>
			<td>
			<c:if test="${empty  item.before_run_type }">不处理</c:if>
			<c:if test="${item.before_run_type == '0'}">不处理</c:if>
			<c:if test="${item.before_run_type == '1'}">执行其他步骤</c:if>
			<c:if test="${item.before_run_type == '2'}">替换业务常量或变量</c:if>
			<c:if test="${item.before_run_type == '3'}">替换SQL变量</c:if>
	        </td> 
			<td>${item.before_run }</td>
			<td>
			<c:if test="${empty  item.after_run_type }">不处理</c:if>
			<c:if test="${item.after_run_type == '0'}">不处理</c:if>
			<c:if test="${item.after_run_type == '1'}">处理token</c:if>
			<c:if test="${item.after_run_type == '2'}">更新业务变量</c:if>
			</td>
			<td>${item.after_run }</td>
			<td style="display:none">${item.case_id }</td>
			<td>
				<a href="javascript:edit('${item.case_id}')">编辑</a>
				<a href="javascript:del('${item.case_id }','${item.case_des }')">删除</a>
			</td>
		</tr>
	</c:forEach>
</table>