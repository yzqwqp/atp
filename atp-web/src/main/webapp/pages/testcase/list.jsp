<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="table table-bordered" id="methodid" >
	<tr>
		<th>用例id</th>
		<th>用例描述</th>
		<th>用例数据（json格式）</th>
		<th>用例断言类型</th>
		<th>用例断言的值</th>
		<th>操作</th>
	</tr>
	<c:forEach var="item" items="${caseList }" >
		<tr class="">
			<td>${item.case_id }</td>
			<td>${item.case_des }</td>
			<td>${item.case_data }</td>
			<td>${item.case_assert_type == '0' ? '不断言' : '断言返回结果'  }</td>
			<td>${item.case_assert_value }</td>
			<td>
				<a href="javascript:edit('${item.case_id}')">编辑</a>
				<a href="javascript:del('${item.case_id }','${item.case_des }')">删除</a>
				<a href="javascript:run('${item.case_id}')">执行</a>
			</td>
		</tr>
	</c:forEach>
</table>