var queryCondition = "";

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
	var child = $('#duox').children();
	$('#duox').html(arr.join(',')).append(child);
}

/**
 * 查询数据
 */
function queryT0Check(pageindex) {
	var channelno = $('#channelno').val();
	var consumerno = $('#consumerno').val();
	if (!channelno && !consumerno) {
		alert("代发渠道与消费商户必需选一项");
		return;
	}
	var returnmoneydate = $('#returnmoneydate').val();
	var transactiondate = $('#transactiondate').val();
	if (!returnmoneydate && !transactiondate) {
		alert("交易日期与回款日期不能同时为空");
		return;
	}
	var params = $('#queryForm').serialize();
	queryCondition = params;
	//清空列表
	$('#column').empty();
	$('#databox').empty();
	params += '&last=' + $('#pageSize').val();
	params += '&first=' + pageindex;
	ajaxReq(params, path+'/report/t0check/data.do',
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
	    		if (i == data.datalist.length-1) {
	    			$.each(item,function(k, obj){
	    				if (k == 0) {
	    					html += '<td><b>合计</b></td>';
						} else {
							html += '<td><b>' + (obj==null?"":obj) + '</b></td>';
						}
		    		})
				}else{
					$.each(item,function(k, obj){
		    			html += '<td>' + (obj==null?"":obj) + '</td>';
		    		})
				}
	    		html += '</tr>';
	    		$('#databox').append(html);
	    	});
	    	if (data.total==0) {
	    		$('#expdata').css('display','none');
	    		$('#databox').append("<span style='color:red'>无数据!</span>");
	    	} else {
				$('#expdata').css('display','block');
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
			event.stopImmediatePropagation();
			if (page!=current) {
				queryT0Check(page);
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
