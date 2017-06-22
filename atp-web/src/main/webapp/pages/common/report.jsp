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

				<c:forEach items="${rows }" var="arr" varStatus="stat">
					 <c:if test="${stat.last}">
					 	<tr class="">
							<c:forEach items="${arr}" var="item">
								<td><b>${item }</b></td>
							</c:forEach>
						</tr>
					 </c:if>
					<c:if test="${!stat.last}">
						<tr class="">
							<c:forEach items="${arr}" var="item">
								<td>${item }</td>
							</c:forEach>
						</tr>
					 </c:if>

				</c:forEach>

			</tbody>
		</table>
	</div>
</div>