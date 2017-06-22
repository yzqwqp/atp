<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="tabxscroll">
	<div class="table-responsive">
		<table class="table table-bordered ">
			<thead>
				<tr>
					<c:forEach items="${column }" var="item">
						<th>${item}</th>
					</c:forEach>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${rows }" var="arr">
					<tr class="">
						<c:forEach items="${arr}" var="item">
							<td>${item }</td>
						</c:forEach>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>
</div>