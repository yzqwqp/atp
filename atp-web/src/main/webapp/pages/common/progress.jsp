<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="topnav" id="wrappera">
	<div id="scroller">
		<ul class="step">
		<c:if test="${sessionUser.userAuthorityVo.isSuperAdmin || sessionUser.userAuthorityVo.moduleMap['qslc_1'] }">
			<li id="flow1"><a href="<%=path%>/dailyinit/index.do"> <span>1</span>
					<p>每日初始化</p>
			</a> <strong></strong></li>
		</c:if>
		<c:if test="${sessionUser.userAuthorityVo.isSuperAdmin || sessionUser.userAuthorityVo.moduleMap['hqjs_1'] }">
			<li id="flow2"><a href="<%=path%>/receive/index.do"> <span>2</span>
					<p>行情及确认文件接收</p>
			</a> <strong></strong></li>
		</c:if>
		<c:if test="${sessionUser.userAuthorityVo.isSuperAdmin || sessionUser.userAuthorityVo.moduleMap['hqdr_1'] }">
			<li id="flow3"><a href="<%=path%>/quotation/index.do"> <span>3</span>
					<p>行情导入</p>
			</a> <strong></strong></li>
		</c:if>
		<c:if test="${sessionUser.userAuthorityVo.isSuperAdmin || sessionUser.userAuthorityVo.moduleMap['qsqbf_1'] }">
			<li id="flow4"><a href="<%=path%>/ack/before/index.do"> <span>4</span>
					<p>清算前备份</p>
			</a> <strong></strong></li>
		</c:if>
		<c:if test="${sessionUser.userAuthorityVo.isSuperAdmin || sessionUser.userAuthorityVo.moduleMap['qrwjdr_1'] }">
			<li id="flow5"><a href="<%=path%>/ack/import/index.do"> <span>5</span>
					<p>确认文件导入</p>
			</a> <strong></strong></li>
		</c:if>
		<c:if test="${sessionUser.userAuthorityVo.isSuperAdmin || sessionUser.userAuthorityVo.moduleMap['qrcl_1'] }">
			<li id="flow6" ><a href="<%=path%>/ack/do/index.do"> <span>6</span>
					<p>确认处理</p>
			</a> <strong></strong></li>
		</c:if>
		<c:if test="${sessionUser.userAuthorityVo.isSuperAdmin || sessionUser.userAuthorityVo.moduleMap['fedz_1'] }">
			<li  id="flow7" ><a href="<%=path%>/ack/balfund/index.do"> <span>7</span>
					<p>份额对账</p>
			</a> <strong></strong></li>
		</c:if>
		<c:if test="${sessionUser.userAuthorityVo.isSuperAdmin || sessionUser.userAuthorityVo.moduleMap['qrhcl_1'] }">
		<li  id="flow8" ><a href="<%=path%>/ack/after/index.do"> <span>8</span>
					<p>确认后处理</p>
			</a> <strong></strong></li>
		</c:if>
		<c:if test="${sessionUser.userAuthorityVo.isSuperAdmin || sessionUser.userAuthorityVo.moduleMap['qrsf_1'] }">
			<li id="flow9"><a href="<%=path%>/ack/payment/index.do"> <span>9</span>
                    <p>确认收付数据生成</p>
            </a> <strong></strong></li>
        </c:if>
        <c:if test="${sessionUser.userAuthorityVo.isSuperAdmin || sessionUser.userAuthorityVo.moduleMap['kkdz_1'] }">
			<li id="flow10" ><a href="<%=path%>/payinchk/flow.do"> <span>10</span>
					<p>扣款对账</p>
			</a> <strong></strong></li>
		</c:if>
		<c:if test="${sessionUser.userAuthorityVo.isSuperAdmin || sessionUser.userAuthorityVo.moduleMap['dfdz_1'] }">
			<li  id="flow11" ><a href="<%=path%>/proxy/index.do"> <span>11</span>
					<p>代发对账</p>
			</a> <strong></strong></li>
		</c:if>
		<c:if test="${sessionUser.userAuthorityVo.isSuperAdmin || sessionUser.userAuthorityVo.moduleMap['ssbf_1'] }">
			<li id="flow12"><a href="<%=path%>/closemarket/index.do"> <span>12</span>
                    <p>收市备份</p>
            </a> <strong></strong></li>
        </c:if>
		<c:if test="${sessionUser.userAuthorityVo.isSuperAdmin || sessionUser.userAuthorityVo.moduleMap['sqycl_1'] }">
			<li  id="flow13" ><a href="<%=path%>/apply/prepro/preinit.do"> <span>13</span>
					<p>申请预处理</p>
			</a> <strong></strong></li>
		</c:if>
		<c:if test="${sessionUser.userAuthorityVo.isSuperAdmin || sessionUser.userAuthorityVo.moduleMap['sqwjdc_1'] }">
			<li  id="flow14" ><a href="<%=path%>/apply/file/index.do"> <span>14</span>
					<p>申请文件导出</p>
			</a> <strong></strong></li>
		</c:if>
		<c:if test="${sessionUser.userAuthorityVo.isSuperAdmin || sessionUser.userAuthorityVo.moduleMap['sqwjfs_1'] }">
			<li  id="flow15" ><a href="<%=path%>/apply/send/index.do"> <span>15</span>
					<p>申请文件发送</p>
			</a> <strong></strong></li>
		</c:if>
		<c:if test="${sessionUser.userAuthorityVo.isSuperAdmin || sessionUser.userAuthorityVo.moduleMap['sqsfsj_1'] }">
			<li  id="flow16" ><a href="<%=path%>/apply/payment/index.do"> <span>16</span>
					<p>申请收付数据生成</p>
			</a> <strong></strong></li>
		</c:if>
		<c:if test="${sessionUser.userAuthorityVo.isSuperAdmin || sessionUser.userAuthorityVo.moduleMap['rz_1'] }">
			<li  id="flow17" ><a href="<%=path%>/dailyend/index.do"> <span>17</span>
					<p>日终</p>
			</a> <strong></strong></li>
		</c:if>
		</ul>
	</div>
</div>
<script>
$(function(){
    ajaxReq({}, domain + '/flowinfos.do', 
   		function(data){
            if (data.success) {
                $.each(data.model, function(key, value) { 
                    var cls = '';
                    if (value === '2') {//已完成
                        cls ='active';
                    } else if (value === '4') {//部分完成
                        cls = 'halfstep';
                    }
                    $('#flow' + (parseInt(key)+1)).addClass(cls);
                }); 
            } else {
                alert(data.errMsg);
            }
        });
});
</script>
