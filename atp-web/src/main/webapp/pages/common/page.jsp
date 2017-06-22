<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="fenye clearfix">
    <nav>
        <c:if test="${pageVo.total>0}">
        <div class="sel">
        每页显示
            <select id="pagesize" onchange="choisePage()">
                <option <c:if test="${pagesize==10 }">selected</c:if> value="10">10</option>
                <option <c:if test="${pagesize==20 }">selected</c:if> value="20">20</option>
                <option <c:if test="${pagesize==50 }">selected</c:if> value="50">50</option>
                <option <c:if test="${pagesize==100 }">selected</c:if> value="100">100</option>
                <option <c:if test="${pagesize==200 }">selected</c:if> value="200">200</option>
            </select>
            行
        </div>
        <ul class="pagination">
        <c:if test="${!pageVo.before && !pageVo.after}">
			<li><a href="javascript:void(0)" onclick="createForm('${pageVo.current-1}${params}')" aria-label="Previous"><span aria-hidden="true">上一页</span></a></li>
			<li><a href="javascript:void(0)" onclick="createForm('1${params}')">1</a></li>
			<li class="more">...</li>
			<c:forEach items="${pageVo.showNums}" var="num">
				<c:choose>
					<c:when test="${pageVo.current==num}">
						<li class="active"><a href="javascript:void(0)">${num} <span class="sr-only">(current)</span></a></li>
					</c:when>
					<c:otherwise>
						<li><a href="javascript:void(0)" onclick="createForm('${num}${params}')">${num }</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<li class="more">...</li>
			<li><a href="javascript:void(0)" onclick="createForm('${pageVo.totalPage}${params}')">${pageVo.totalPage}</a></li>
			<li><a href="javascript:void(0)" onclick="createForm('${pageVo.current+1}${params}')" aria-label="Next"><span aria-hidden="true">下一页</span></a></li>
		</c:if>
        
        
        <!-- {total=227, pageSize=1, len=3, current=226, before=false, after=true, totalPage=227, showNums=[223, 224, 225, 226, 227]} -->
		<c:if test="${!pageVo.before && pageVo.after}">
			<li><a href="javascript:void(0)" onclick="createForm('${pageVo.current-1}${params}')" aria-label="Previous"><span aria-hidden="true">上一页</span></a></li>
			<li><a href="javascript:void(0)" onclick="createForm('1${params}')">1</a></li>
			<li class="more">...</li>
			<c:forEach items="${pageVo.showNums}" var="num">
				<c:choose>
					<c:when test="${pageVo.current==num}">
						<li class="active"><a href="javascript:void(0)">${num} <span class="sr-only">(current)</span></a></li>
					</c:when>
					<c:otherwise>
						<li><a href="javascript:void(0)" onclick="createForm('${num}${params}')">${num}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${pageVo.current!=pageVo.totalPage }">
				<li><a href="javascript:void(0)" onclick="createForm('${pageVo.current+1}${params}')" aria-label="Next"><span aria-hidden="true">下一页</span></a></li>
			</c:if>
		</c:if>
		<!-- {total=227, pageSize=1, len=3, current=3, before=true, after=false, totalPage=227, showNums=[1, 2, 3, 4, 5]} -->
		<c:if test="${pageVo.before && !pageVo.after}">
			<c:if test="${pageVo.current!=1 }">
				<li><a href="javascript:void(0)" onclick="createForm('${pageVo.current-1}${params}')" aria-label="Previous"><span aria-hidden="true">上一页</span></a></li>
			</c:if>
			<c:forEach items="${pageVo.showNums}" var="num">
				<c:choose>
					<c:when test="${pageVo.current==num}">
						<li class="active"><a href="javascript:void(0)">${num} <span class="sr-only">(current)</span></a></li>
					</c:when>
					<c:otherwise>
						<li><a href="javascript:void(0)" onclick="createForm('${num}${params}')">${num }</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<li class="more">...</li>
			<li><a href="javascript:void(0)" onclick="createForm('${pageVo.totalPage}${params}')">${pageVo.totalPage}</a></li>
			<li><a href="javascript:void(0)" onclick="createForm('${pageVo.current+1}${params}')" aria-label="Next"><span aria-hidden="true">下一页</span></a></li>
		</c:if>
		<!-- 测试分页:{total=6, pageSize=1, len=3, current=3, before=true, after=true, totalPage=6, showNums=[1, 2, 3, 4, 5, 6]} -->
		<c:if test="${pageVo.before && pageVo.after}">
			<c:if test="${pageVo.current!=1 }">
				<li><a href="javascript:void(0)" onclick="createForm('${pageVo.current-1}${params}')" aria-label="Previous"><span aria-hidden="true">上一页</span></a></li>
			</c:if>
			<c:forEach items="${pageVo.showNums}" var="num">
				<c:choose>
					<c:when test="${pageVo.current==num}">
						<li class="active"><a href="javascript:void(0)">${num} <span class="sr-only">(current)</span></a></li>
					</c:when>
					<c:otherwise>
						<%-- <a href="<%=href%>${num}${params}">${num}</a> --%>
						<li><a href="javascript:void(0)" onclick="createForm('${num}${params}')">${num}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${pageVo.current!=pageVo.totalPage }">
				<li><a href="javascript:void(0)" onclick="createForm('${pageVo.current+1}${params}')" aria-label="Next" aria-label="Next"><span aria-hidden="true">下一页</span></a></li>
			</c:if>
		</c:if>
        </ul>
        </c:if>
    </nav>
</div>
