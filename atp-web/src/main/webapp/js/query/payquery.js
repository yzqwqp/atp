
function choiseTano() {
	$("#fundcode").html('<option value="">全部</option>');
	$("#msgfundcodename").html("<strong class='glyphicon glyphicon-triangle-bottom'></strong>");
	$('#fundcodes').html('');
	var tano = $('#tano').val();
	if (!tano) {
		return;
	}
	var url = path + "/trs/fundOption.do"
	ajaxReq({tano: tano}, url, fundcall, "post");
}

function fundcall(data) {
	if (!data.success) {
		alert(data.errMsg);
        return;
    }
	$.each(data.model, function(i, item){
		$('#fundcode').append('<option value="' + item.fundcode + '">' + item.fundshorname + '</option>');
		var html = "<label value='" + item.fundshorname
				+ "' ><strong><input type='checkbox' data-fundname='" + item.fundshorname + "' value='" + item.fundcode
				+ "' name='fundcodes' onchange='selectTa(this)'/></strong>"
				+ item.fundshorname + "</label>";
		$('#fundcodes').append(html);
		cho();
	});
}

function selectTa(obj) {
	if ($(obj).is(':checked')) {
		$(obj).parent().parent().addClass("chos");
		if ($("[name='fundcodes']").not("input:checked").length==0) {
			$("#alltacheck").prop('checked', true);
			$("#alltacheck").parent().parent().addClass("chos");
		}
	} else {
		$(obj).parent().parent().removeClass("chos");
		$("#alltacheck").prop('checked', false);
		$("#alltacheck").parent().parent().removeClass("chos");
	}
	var arr = new Array();
	$("[name='fundcodes']").each(function(){
		if ($(this).is(':checked')) {
			arr.push($(this).data('fundname'));
		}
	});
	var child = $('#msgfundcodename').children();
	$('#msgfundcodename').html(arr.join(',')).append(child);
}

var queryParam="";
var queryArray;
var strongFlag  = false;//表格最后一行是否加粗

$(function(){
	if($('#isStrong').length>0) {
		strongFlag = true;
	}
})
/**
 * 查询
 * @param current 当前页
 */
function checkQuery(current) {
	var $begin = $("#beginopertime");
	var $end = $("#endoperdate");
	if ($begin.size()) {//若日期框在页面存在
		var beginopertime=$("#beginopertime").val();
		var endoperdate=$("#endoperdate").val();
		if(!beginopertime||!endoperdate){
			return;
		} else if(endoperdate<beginopertime) {
			return;
		}
	}
	var pageindex = current?current:1; //若current不存在则为1
	//清空表格
	$('#infos').empty();
	$('#column').empty();
	$('#export').hide();
	//获取查询参数
	var params = queryParam;
	params += '&pageSize=' + $('#pageSize').val();
	params += '&current=' + pageindex;
	ajaxReq(params, $('#queryForm').attr('action'),
		function(data) {
	    	if (!data.success) {
	    		alert(data.errMsg);
	            return;
	        }
    		$.each(data.model.column, function(i, item){
    	   		$('#column').append('<th>' + item + '</th>');
    	   	});
    		var length = data.model.rows.length;
	    	$.each(data.model.rows, function(i, item){
	    		var html = '<tr>';
	    		if (strongFlag && (i== length-1)) {
	    			html = "<tr style='font-weight:bold;'>";
	    		}
	    		$.each(item,function(i, obj){
	    			html += '<td>' + (obj==null?"":obj) + '</td>';
	    		})
	    		html += '</tr>';
	    		$('#infos').append(html);
	    	});
	    	if (data.model.total==0) {
	    		$('#infos').append("<span style='color:red'>无数据!</span>");
	    	} else {
	    		$('#export').show();
	    	}
	    	paginatorInit(pageindex, data.model.total);
	    }, "post");
}

function query() {
	var $begin = $("#beginopertime");
	var $end = $("#endoperdate");
	if ($begin.size()) {//若日期框在页面存在
		var beginopertime=$("#beginopertime").val();
		var endoperdate=$("#endoperdate").val();
		if(!beginopertime||!endoperdate){
			alert("查询时间不能为空");
			return;
		} else if(endoperdate<beginopertime) {
			alert("结束日期必须>=开始日期");
			return;
		}
	}
	$('#ctest').attr("name",$('#ctest').attr("column"));
	$form = $('#queryForm');
	queryArray = $form.serializeArray();
	queryParam = $form.serialize();
	checkQuery();
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
				checkQuery(page);
			}
		},
	}
	$('#paginator').bootstrapPaginator(options);
}

function exportdata(title) {
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
	$.each(queryArray, function(index, val) {
		var input = document.createElement("input");
		input.type = "hidden";
		input.name = val.name;
		input.value = val.value;
		form.appendChild(input);
	});
	form.submit();
}
