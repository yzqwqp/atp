var queryCondition = "";

/**
 * 查询数据
 */
function queryAcctApp(pageindex) {
	$('#ctest').attr("name",$('#ctest').attr("column"));
	var params = $('#queryForm').serialize();
	queryCondition = params;
	//清空列表
	$('#column').empty();
	$('#databox').empty();
	params += '&pagecount=' + $('#pageSize').val();
	params += '&pageindex=' + pageindex;
	ajaxReq(params, path+'/query/acct/app/appdata.do',
		function(data) {
	    	if (data.msg) {
	    		alert(data.msg);
	            return;
	        }
    		$.each(data.columns, function(i, item){
    	   		$('#column').append('<th>' + item + '</th>');
    	   	});
	    	$.each(data.datalist, function(i, item){
	    		var html = '<tr>';
	    		$.each(item,function(i, obj){
	    			html += '<td>' + (obj==null?"":obj) + '</td>';
	    		})
	    		html += '</tr>';
	    		$('#databox').append(html);
	    	});
	    	if (data.total==0) {
	    		$('#databox').append("<span style='color:red'>无数据!</span>");
	    	}
	    	paginatorInit(pageindex, data.total);
	    }, "post");
}
/**
 * 分页组件初始化
 * @param current 当前页
 * @param count 总记录数
 */
function paginatorInit(current, count) {
	var options = {
		bootstrapMajorVersion : 3, //版本
		currentPage : current,
		totalPages : Math.ceil((count==0?1:count)/$('#pageSize').val()),
		numberOfPages : 5,
		shouldShowPage: function(type, page, current) {
			switch(type) {
		    case "first":
		        return false;
		    case "prev":
		    	return true;
		    case "next":
                return true;
            case "last":
                return false;
            case "page":
                return true;
			}
		},
		itemTexts : function(type, page, current) {
			switch (type) {
			case "prev":
				return "上一页";
			case "next":
				return "下一页";
			case "page":
				return page;
			}
		},
		onPageClicked: function(event, originalEvent, type, page) {
			if (page!=current) {
				queryAcctAck(page);
			}
		},
	}
	$('#paginator').bootstrapPaginator(options);
}

function exportdata(title) {
	var params = queryCondition;
	if (!params) {
		params = $('#queryForm').serialize();
	}
	var kv = params.split("&");
	var form = document.createElement("form");
	form.action = "export.do";
	form.method = "post";
	form.target = "_blank";
	document.body.appendChild(form);
	var input = document.createElement("input");
	input.type = "hidden";
	input.name = "title";
	input.value = title;
	form.appendChild(input);
	for (var i = 0; i < kv.length; i++) {
		var keyvalue = kv[i];
		var keyvalueArray = keyvalue.split("=");
		if (keyvalueArray.length < 2 || keyvalueArray[1] == "" || keyvalueArray[1] == null) {
			continue;
		}
		var input = document.createElement("input");
		input.type = "hidden";
		input.name = keyvalueArray[0];
		input.value = keyvalueArray[1];
		form.appendChild(input);
	}
	form.submit();
}

/**
 * 下拉框多选
 * @param obj
 */
function chooseBizCode(obj) {
	if ($(obj).is(':checked')) {
		$(obj).parent().parent().addClass("chos");
	} else {
		$(obj).parent().parent().removeClass("chos");
	}
	getBizCodeValue();
}

function getBizCodeValue() {
	var arr = new Array();
	$("[name='businesscode']").each(function(){
		if ($(this).is(':checked')) {
			arr.push($(this).val());
		}
	});
	var child = $('#bcode').children();
	$('#bcode').html(arr.join(',')).append(child);
}

function chooseIntvp(obj) {
	if ($(obj).is(':checked')) {
		$(obj).parent().parent().addClass("chos");
	} else {
		$(obj).parent().parent().removeClass("chos");
	}
	getIntvpValue();
}

function getIntvpValue() {
	var arr = new Array();
	$("[name='intvp']").each(function(){
		if ($(this).is(':checked')) {
			arr.push($(this).val());
		}
	});
	var child = $('#dintvp').children();
	$('#dintvp').html(arr.join(',')).append(child);
}

function chooseStatus(obj) {
	if ($(obj).is(':checked')) {
		$(obj).parent().parent().addClass("chos");
	} else {
		$(obj).parent().parent().removeClass("chos");
	}
	getStatusValue();
}

function getStatusValue() {
	var arr = new Array();
	$("[name='status']").each(function(){
		if ($(this).is(':checked')) {
			arr.push($(this).val());
		}
	});
	var child = $('#dstatu').children();
	$('#dstatu').html(arr.join(',')).append(child);
}

function chooseClStatus(obj) {
	if ($(obj).is(':checked')) {
		$(obj).parent().parent().addClass("chos");
	} else {
		$(obj).parent().parent().removeClass("chos");
	}
	getClStatusValue();
}

function getClStatusValue(){
	var arr = new Array();
	$("[name='clearstatus']").each(function(){
		if ($(this).is(':checked')) {
			arr.push($(this).val());
		}
	});
	var child = $('#dclstatu').children();
	$('#dclstatu').html(arr.join(',')).append(child);
}

function chooseMethod(obj){
	if ($(obj).is(':checked')) {
		$(obj).parent().parent().addClass("chos");
	} else {
		$(obj).parent().parent().removeClass("chos");
	}
	getMethodValue();
}

function getMethodValue(){
	var arr = new Array();
	$("[name='acceptmethod']").each(function(){
		if ($(this).is(':checked')) {
			arr.push($(this).val());
		}
	});
	var child = $('#dmethod').children();
	$('#dmethod').html(arr.join(',')).append(child);
}