<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String domain = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="Xenon Boostrap Admin Panel" />
<meta name="author" content="" />
<title>自动化测试系统</title>
<link rel="Shortcut Icon" href="<%=path%>/images/favicon.ico" />
<link rel="stylesheet"
	href="<%=path%>/css/fonts/fontawesome/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=path%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=path%>/css/xenon-core.css">
<link rel="stylesheet" href="<%=path%>/css/animation.css">
<link rel="stylesheet" href="<%=path%>/css/tou-style.css">
<script src="<%=path%>/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/common/page.js"></script>
<script type="text/javascript">
	var path='<%=path%>';
	function edit(id){
		var data={};
		data.sid=id;
		var url=path+"/testcase/selectById.do";
		ajaxReq(data, url, showSingle, "post");
	}
	function showSingle(result){
		var data=result.obj;
		$("#casedes2").val(data.case_des);
		$("#caseid2").val(data.case_id);
		$("#casedata2").val(data.case_data);
		$("#caseasserttype2").val(data.case_assert_type);
		$("#caseassertvalue2").val(data.case_assert_value);
		$("#isrun2").val(data.is_run);
		$(".edit").modal("show");
	}
	function update(){
		var data = {};
		data.case_id = $("#caseid2").val();
		data.case_des = $("#casedes2").val();
		data.case_data = $("#casedata2").val();
		data.case_assert_type = $("#caseasserttype2").val();
		data.case_assert_value = $("#caseassertvalue2").val();
		data.is_run = $("#isrun2").val();
		var url = path + "/testcase/update.do";
		ajaxReq(data, url, doCall, "post");
	}
	function del(id,name) {
		$(".dele").modal("show");
		$("#casedes3").html(name);
		$("#delyes").unbind("click").click(function() {
			$('.dele').modal('hide');
			var data = {};
			data.sid = id;
			data.sname = name;
			var url = path + "/testcase/deleteById.do";
			ajaxReq(data, url, doCall, "post");
		});
	}
	function run(id) {
			var data = {};
			data.sid = id;
			var url = path + "/testcase/run.do";
			ajaxReq(data, url, null, "post");
	}
	function selectChangeService(event){
		var value=$(event).val();
		var url=path+"/testmethod/createdMethod.do";
		var data={};
		if(value==""){
			data.sname="";
		}else{
			data.sname=value;
		}
		ajaxReq(data, url, selectChangeServiceCall, "post");
	}
	function selectChangeServiceCall(result){
		var data = result.obj;
		var options = "";
		for(var i=0;i<data.length;i++){
			options+=("<option value='"+data[i]+"'>"+data[i]+"</option>");
		}
		$("#initmethodselect").html(options);
	}
	function chBlur(a, b, c) {
		if ($("#" + a).val() == "") {
			$("#" + b).html(c)
			return false;
		}
		$("#" + b).html("")
		return true;
	}
	function skipIndex(){
		window.location.href=path+"/testcase/index.do"
	}
	function addBefore() {
		$(".xinz").modal("show");
		var sService=$("#initserviceselect").val();
		var sMethod=$("#initmethodselect").val();
		var data={};
		data.service_name=sService;
		data.method_name=sMethod;
		var url=path+"/testmethod/selectByServiceNameAndMethodName.do";
		ajaxReq(data, url, addBeforeCall, "post");
	}
	function addBeforeCall(result) {
		var data=result.obj;
		var sService=$("#initserviceselect").val();
		var sMethod=$("#initmethodselect").val();
		$("#addServiceName").attr("value",sService);
		$("#addMethodName").attr("value",sMethod);
		$("#methodDes").val(data.method_des);
		//$(".xinz").modal('show');
	}
	function add() {
		var data = {};
		data.service_name = $("#addServiceName").val();
		data.method_name = $("#addMethodName").val();
		data.case_des = $("#casedes").val();
		data.is_run = $("#isrun").val();
		data.case_assert_value = $("#caseassertvalue").val();
		data.case_assert_type = $("#caseasserttype").val();
		var url = path + "/testcase/add.do";
		ajaxReq(data, url, doCall, "post");
	}
	function doCall(result){
		$("#casedes3").html(result.obj);
		$(".xinxi").modal('show');
	}
	function editCaseData(){
		$(".editData").modal('show');
		var data = {};
		data.case_id = $("#caseid2").val();
		var url = path + "/testdata/editCaseData.do";
		ajaxReq(data, url, editCaseDataCall, "post");
	}
	function editCaseDataCall(result){
		var data = result.obj;
		var str="";
		for (var i=0; i<data.length; i++){
			str+="<tr>"
			str+="<td>"+data[i].case_id+"</td>"
			str+="<td>"+data[i].data_id+"</td>"
			str+="<td>"+data[i].para_name+"</td>"
			str+="<td>"+data[i].para_type+"</td>"
			str+="<td>"+data[i].field_name+"</td>"
			str+="<td>"+data[i].field_type+"</td>"
			str+="<td><input id="+data[i].data_id+" type='text' value='"+data[i].value_data+"' /></td>"
			str+="</tr>"
		}
		//$(".editData").modal('show');
		$("#dataList").html(str);
	}
	function updateData(){
		var map = new Object();
		var trs =$("#dataList tr");
		trs.each(function (){
			var id = $(this).find("td:last input").attr('id');
			var value = $(this).find("td:last input").val();
			map[id]=value;
			}
		);
		var json = JSON.stringify(map);
		//console.log(json);
		var url = path + "/testdata/updateData.do";
		ajaxReq({'param':json}, url, doCall, "post");
	}
	
	
</script>
</head>
<body class="page-body">
<input type="hidden" value="${params }" id="skipParam">
	<div class="page-container">
		<!--progress-->
		<%@include file="../common/left-menu.jsp"%>
		<div class="main-content">
			<div class="topv">
				<h3>测试方法</h3>
			</div>
			<div class="row" style="padding-top: 50px;">
				<div class="col-md-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<!--<div class="panel-options"> <a href="#"> <i class="linecons-cog"></i> </a> <a href="#" data-toggle="panel"> <span class="collapse-icon">–</span> <span class="expand-icon">+</span> </a> <a href="#" data-toggle="reload"> <i class="fa-rotate-right"></i> </a> <a href="#" data-toggle="remove"> × </a> </div>-->
						</div>
						<div class="screen">
						<form action="<%=path %>/testcase/index.do" id="payForm" method="post">
							<ul class="shaix clearfix">
								<li><span>服务名称：</span> <select name="initserviceselect" id="initserviceselect" onchange="selectChangeService(this)">
										<c:forEach items="${serviceList }" var="item" >
											<option value="${item.service_name }" onclick="queryMethod('${item.service_name}')" <c:if test="${item.service_name !=null }">selected</c:if> >${item.service_name }</option>
										</c:forEach>
								</select></li>
								<li><span>方法名称：</span> <select name="initmethodselect" id="initmethodselect" >
										<c:forEach items="${initMethodList }" var="item">
											<option value="${item.method_name }" <c:if test="${item.method_name !=null }">selected</c:if> >${item.method_name }</option>
										</c:forEach>
								</select></li>
								<li><a href="javascript:void(0)" class="sxbtn" onclick="query()"> <span
										class="glyphicon glyphicon-search"></span> 筛选
								</a></li>
								<li>
									<button type="button" class="addnew" style="font-size:14px;dipslay:inline;"
									 onclick="addBefore('${initserviceselect}','${initmethodselect}')" >新增用例</button>
								</li>
							</ul>
						</form>
						</div>

						<div class="table-responsive">
							<table class="table table-bordered">
								<tr>
									<th>用例id</th>
									<th>用例描述</th>
									<th>测试数据</th>
									<th>断言类型</th>
									<th>断言数据</th>
									<th>是否运行</th>
									<th>操作</th>
								</tr>
								<c:forEach var="item" items="${caseList }">
									<tr class="">
										<td>${item.case_id }</td>
										<td>${item.case_des }</td>
										<td>${item.case_data }</td>
										<td>${item.case_assert_type }</td>
										<td>${item.case_assert_value }</td>
										<td>${item.is_run }</td>
										<td>
											<a href="javascript:run('${item.case_id }')">执行</a>
											<a href="javascript:edit('${item.case_id}')">编辑</a>
											<a href="javascript:del('${item.case_id }','${item.case_des }')">删除</a>
										</td>
									</tr>
								</c:forEach>
							</table>
						</div>
						<%@include file="../common/page.jsp" %>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 新增 -->
	<div class="modal fade xinz" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel">
		<div class="modal-dialog xinzm">
			<div class="modal-content">
				<div class="xzmain">
					<h3>测试用例-新增</h3>
					<div class="bwarp">
							<ul>
							<li class="clearfix bgwhite">
								<div class="fp">
									<span><strong>*</strong>测试服务:</span> <input type="text"	id="addServiceName" disabled="disabled"/>
								</div>
							</li>
							<li class="clearfix bgwhite">
								<div class="fp">
									<span><strong>*</strong>测试方法:</span> <input type="text"	id="addMethodName"  disabled="disabled"/>
								</div>
								<div class="fp">
									<span><strong>*</strong>方法描述:</span> <input type="text"	id="methodDes"  disabled="disabled"/>
								</div>
							</li>
							<li class="clearfix bgwhite">
								<div class="fp">
									<span><strong>*</strong>用例描述:</span> <input type="text"	id="casedes"  />
								</div>
								<div class="fp">
									<span><strong>*</strong>是否运行:</span>
									<select id="isrun" >
										<option value="1">YES</option>
										<option value="0">NO</option>
									</select>
								</div>
							</li>
							<li class="clearfix bgwhite">
								<div class="fp">
									<span><strong>*</strong>断言数据:</span> <input type="text" id="caseassertvalue" onblur="chBlur('caseassertvalue','caseassertvalue1','断言数据不能为空')"/><span id="caseassertvalue1" style="color: red;font-size:13px"></span>
								</div>
								<div class="fp">
									<span><strong>*</strong>断言类型:</span> <input type="text" id="caseasserttype" onblur="chBlur('caseasserttype','caseasserttype1','断言类型不能为空')"/><span id="caseasserttype1" style="color: red;font-size:13px"></span>
								</div>
							</li>
						</ul>
						<div class="modal-footer">
							<p>
								<button type="button" class="save" onclick="add()">保存</button>
								<button type="button" class="closebtn" data-dismiss="modal">取消</button>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 修改 -->
	<div class="modal fade edit" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel">
		<div class="modal-dialog xinzm">
			<div class="modal-content">
				<div class="xzmain">
					<h3>测试用例-编辑</h3>
					<div class="bwarp">
							<ul>
							<li class="clearfix bgwhite">
								<div class="fp">
									<span><strong>*</strong>用例描述:</span> <input type="text"	id="casedes2"  />
								</div>
								<div class="fp">
									<span><strong>*</strong>用例id:</span> <input type="text"	id="caseid2"  disabled="disabled"/>
								</div>
							</li>
							<li class="clearfix bgwhite">
								<div class="fp">
									<span><strong>*</strong>测试数据:</span>
									<button type="button" class="addnew" style="font-size:14px;dipslay:inline;"
									 onclick="editCaseData()" >编辑用例数据</button>
									 
								</div>						
								<div class="fp">
									<span><strong>*</strong>断言类型:</span> <input type="text" id="caseasserttype2" onblur="chBlur('caseasserttype','caseasserttype1','断言类型不能为空')"/><span id="caseasserttype1" style="color: red;font-size:13px"></span>
								</div>
							</li>
							<li class="clearfix bgwhite">
								<div class="fp">
									<span><strong>*</strong>断言数据:</span> <input type="text" id="caseassertvalue2" onblur="chBlur('caseassertvalue','caseassertvalue1','断言数据不能为空')"/><span id="caseassertvalue1" style="color: red;font-size:13px"></span>
								</div>						
								<div class="fp">
									<span><strong>*</strong>是否运行:</span>
									<select id="isrun2" >
										<option value="1">YES</option>
										<option value="0">NO</option>
									</select>
								</div>
							</li>
						</ul>
						<div class="modal-footer">
							<p>
								<button type="button" class="save" onclick="update()">保存</button>
								<button type="button" class="closebtn" data-dismiss="modal">取消</button>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 修改成功后的提示 -->
	<div class="modal fade xinxi" id="" tabindex="-1" role="dialog" style="z-index:5555" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">信息提示</h4>
				</div>
				<div class="modal-body">
					<div class="xints">
						<h4 id="msg"></h4>
						<p>
							<span>测试用例:<strong id="casedes3"></strong></span>
						</p>
					</div>
				</div>
				<div class="modal-footer">
					<p>
						<button type="button" class="save" data-dismiss="modal" onclick="skipIndex()">确认</button>
					</p>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 删除提示 -->
	<div class="modal fade dele" id="" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">信息提示</h4>
				</div>
				<div class="modal-body">
					<div class="xints">
						<h4>是否删除</h4>
						<p>
							<span>测试用例:<strong id="casedes3"></strong></span>
						</p>
					</div>
				</div>
				<div class="modal-footer">
					<p>
						<button type="button" class="save" id="delyes">是</button>
						<button type="button" class="closebtn" data-dismiss="modal">取消</button>
					</p>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 修改用例数据页面 -->
	<div class="modal fade editData" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel">
		<div class="modal-dialog xinzm1">
			<div class="modal-content">
				<div class="xzmain">
					<h3>测试数据-编辑</h3>
					<div class="table-responsive">
						<table class="table table-bordered">
							<thead>
								<th>case_id</th>
								<th>data_id</th>
								<th>参数名称</th>
								<th>参数类型</th>
								<th>元素名称</th>
								<th>元素类型</th>
								<th>参数赋值</th>
							</thead>
							<tbody id="dataList" >
							</tbody>
						</table>
					</div>
					<div class="modal-footer">
						<p><button type="button" class="save" onclick="updateData()">保存</button>
						<button type="button" class="closebtn" data-dismiss="modal">取消</button></p>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	
	<!-- Bottom Scripts -->
	<script src="<%=path%>/js/bootstrap.min.js"></script>
	<script src="<%=path%>/js/TweenMax.min.js"></script>
	<!--<script src="<%=path%>/js/joinable.js"></script>
	<script src="<%=path%>/js/xenon-api.js"></script>-->
	<script src="<%=path%>/js/resizeable.js"></script>
	<script src="<%=path%>/js/xenon-toggles.js"></script>
	<script src="<%=path%>/js/checkbox.js"></script>
	<!--JavaScripts initializations and stuff -->
	<script src="<%=path%>/js/xenon-custom.js"></script>
	<script src="<%=path%>/js/datePicker/wdatePicker.js"></script>
</body>
</html>