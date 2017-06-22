	function qryset(){
		$('.tbset').modal('show');
    	var userid=$("#userid").val();
		var funcid=$("#funcid").val();
		if(!userid||!funcid){
			alert("功能名称与操作员号不能为空");
			return;
		}
		var data={}
		data.userid=userid;
		data.funcid=funcid;
		var url=path+"/query/trs/qryfield.do";
		ajaxReq(data, url, fieldcall, "post");
	}
	
	function fieldcall(json){
		$('.tbset').modal('show');
		$("#trqryfield").html();
		if(json.statusCode=="0000"){
			var data=json.model;
			if(data.length>0){
				var tr=""
				var checkall=true;
				for(var i=0;i<data.length;i++){
					if(data[i].display=='1'){
						tr+="<tr class='onea activea'>";
						tr+="<td><span class='chos' onclick='choiseItem(this)'>";
						tr+="<input type='checkbox' checked='checked' name='qryfield' value='"+data[i].fieldname+"'/>";
					}else{
						checkall=false;
						tr+="<tr class='onea'>";
						tr+="<td><span onclick='choiseItem(this)'>";
						tr+="<input type='checkbox' name='qryfield' value='"+data[i].fieldname+"'/>";
					}

					tr+="</span></td><td>"+data[i].displayname+"</td></tr>"
				}	
				tr+=""
				$("#trqryfield").html(tr);
				if(checkall==true){
	            	$("#idchkall").attr("checked",'true');
	            	$("#idchkall").parent('span').addClass('chos');
				}else{
				    $("#idchkall").removeAttr("checked");
		            $("#idchkall").parent('span').removeClass('chos');
				}
			}

		}
	}
	function choiseItem(event){
		if($(event).hasClass("chos")){
			$(event).children().removeAttr("checked");
			$(event).removeClass("chos");
			$(event).parent().parent().removeClass("activea");
			document.getElementById("idchkall").checked=false;
			$(document.getElementById("idchkall")).parent().removeClass("chos");
		}else{
			$(event).children().attr("checked",'true');
			$(event).addClass("chos");
			$(event).parent().parent().addClass("activea");
		}
	}
	function choiseAll(event){
		var nameas=document.getElementsByName("qryfield");
		if(nameas==null||typeof(nameas)=="undefined"||nameas.length==0){
			alert("没有要选择的数据");
			return;
		}
		if($(event).parent().hasClass("chos")){//
			$(event).parent().removeClass("chos");
			event.checked=false;
			for(var i=0;i<nameas.length;i++){
				if($(nameas[i]).parent().hasClass("chos")){					
					$(nameas[i]).parent().removeClass("chos");
					$(nameas[i]).parent().parent().parent().removeClass("activea");
					$(nameas[i]).removeAttr("checked");
				}
			}
		}else{
			$(event).parent().addClass("chos");
			event.checked=true;
			for(var i=0;i<nameas.length;i++){
				if(!$(nameas[i]).parent().hasClass("chos")){					
					$(nameas[i]).parent().addClass("chos");
					$(nameas[i]).parent().parent().parent().addClass("activea");
					$(nameas[i]).attr("checked",'true');
				}
			}
		}
		
	}
	
	function setField(){
	    var data=""
		$("[name='qryfield']").each(function(){
			if ($(this).is(':checked') || $(this).parent().hasClass('chos')) {
				data+="qryfield="+$(this).val()+"&";
			}
		});
		data+="userid="+$("#userid").val()+"&";
		data+="funcid="+$("#funcid").val()+"&";
		var url=path+"/query/trs/updqryfield.do";
		ajaxReq(data, url, setFieldCall, "post");
	}
	
	function setFieldCall(json){
		if(json.statusCode=="0000"){
			$('.tbset').modal('hide');
		}else{
			alert("设置失败");
		}		
	}
	
	//多选下拉弹窗效果
	function shde(){
		var myDiva = $(".xuanza");
		var myDivb = $(".xuanzb");
	    $(function (){
	        $(".dxbtna").click(function (event){
	            //showDiva();
	            //alert(1);
	            $(this).next('.xuanz').fadeIn();
	            $(this).parents('li').siblings().children('.duoxuan').children('.xuanz').fadeOut();
	            $(document).one("click", function (){
	                //$(myDiva).fadeOut();
	                $('.xuanz').fadeOut();
	            });
	            event.stopPropagation();
	        });
	        $(myDiva).click(function (event){
	            event.stopPropagation();
	        });
	    });

	    $(function (){
	        $(".dxbtnb").click(function (event){
	            showDivb();
	            $(document).one("click", function (){
	                $(myDivb).fadeOut();
	            });
	            event.stopPropagation();
	        });
	        $(myDivb).click(function (event){
	            event.stopPropagation();
	        });
	    });
		function showDivb(){
	        $(myDivb).fadeIn();
	        $(myDiva).fadeOut();
	    }
	 	/*function showDiva(){
	        $(myDiva).fadeIn();
	        $(myDivb).fadeOut();
	    }*/

	    //宝类基金搜索添加，点击显示隐藏效果
	    var divc = $('.seach');
	    $(function (){
	        $(".addgold").click(function (event){
	            //showDiva();
	            $(this).next('.seach').fadeIn();
	            $(document).one("click", function (){
	                //$(myDiva).fadeOut();
	                $('.seach').fadeOut();
	            });
	            event.stopPropagation();
	        });
	        $(divc).click(function (event){
	            event.stopPropagation();
	        });
	        //点击确认隐藏搜索弹框
	        $('.qbtn button').click(function(){
	            $('.seach').fadeOut();
	        })
	    });

	}
	shde();

