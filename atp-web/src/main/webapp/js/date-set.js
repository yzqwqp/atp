/**
 * Created by jason on 2016/4/29.
 */
//日历加载、默认显示当前月之后的12个月。首次加载，不可以选择日期
var dayNames = new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
//设置默认当前年月
var ndate = new Date();
var ymonth = ndate.getFullYear() + '-' + (ndate.getMonth()+1);
var flag="1";
function load(){
	//默认查询工作日数据
	var flag=$("#flag").val();
	var dealflag=$("#dealflag").val();
	var tradeflag=$("#tradeflag").val();
	var natureday=$("#datecs").val();
	var data="flag="+flag+"&dealflag="+dealflag+"&tradeflag="+tradeflag+"&natureday="+natureday;
	var url=path+"/calendar/dateQuery.do";
	ajaxReq(data, url, sdate, "post");		
}
load();
function getMap(json){
	var map = new Map();
	if(json.statusCode=="0000"){
		var data=json.model;
		if(data!=null){
			var flag=$('#flag').val();
			for(var i=0;i<data.length;i++){
				if("1"==flag){
					map.put(data[i].natureday, data[i].dealflag);
				}else{
					map.put(data[i].natureday, data[i].tradeflag);
				}				
			}	
		}
	}	
	return map;
}

function showload(json){
	map=getMap(json);
    $('#calendar').jCal({
        day: new Date(),
        days: 1,
        showMonths: 12,
        monthSelect: false,
        dow: ['日', '一', '二', '三', '四', '五', '六'],
        ml: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
        dCheck:function (day) {//设置周六周日高亮
        	datestr=day.Format("yyyyMMdd");
            if (map.get(datestr)=="0")
                return 'day';
            else
                return 'invday';
        },
        /*callback: function (day, days) {
         //alert(day.getFullYear() + '/' + (day.getMonth() + 1) +'/'+ day.getDate());
         //添加时间到输入框
         $('#nresult input').val(day.getFullYear() + '-' + (day.getMonth() + 1) +'-'+ day.getDate()+','+dayNames[day.getDay()]);
         }*/
    });
}
//showload();
var map;
//选择年月，筛选显示该月以后的12个月
function sdate(json){
	map=getMap(json);
    var year=$('#datecs').val().substring(0,4);
    var month=$('#datecs').val().substring(4,6);
    var setday=new Date(year,parseInt(month)-1);
    $('#calendar').jCal({
        day:setday,
        days: 1,
        showMonths: 12,
        monthSelect: false,
        dow: ['日', '一', '二', '三', '四', '五', '六'],
        ml: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
        dCheck:function (day) {//设置周六周日高亮
        	datestr=day.Format("yyyyMMdd");
            if (map.get(datestr)=="0")
                return 'day';//正常工作日
            else
                return 'invday';//周六日显示
        },
        callback: function (day, days) {
            //添加时间到输入框
        	datestr=day.Format("yyyyMMdd");
            $('#date').val(datestr);
            var flag=$('#flag').val();
            var daystr="工作日";
            if(flag=="2"){
            	daystr="交易日";
            	$("#dayflag").attr("name","tradeflag");  
            }else{
            	$("#dayflag").attr("name","dealflag");  
            }
            var flagstr="";
            if(map.get(datestr)=="0"){
            	$("#dayflag").val("1");
            	flagstr="非";
            }else{
            	$("#dayflag").val("0");
            }
            $("#tips").html("是否设为"+flagstr+daystr);
            //时间传到弹出框
            $('.chodate').html(day.getFullYear() + '-' + (day.getMonth() + 1) +'-'+day.getDate()+','+dayNames[day.getDay()]);
            showmd();
        }

    });

}
function query(){
	var flag=$("#flag").val();
	var dealflag=$("#dealflag").val();
	var tradeflag=$("#tradeflag").val();
	var natureday=$("#datecs").val();
	var data="flag="+flag+"&dealflag="+dealflag+"&tradeflag="+tradeflag+"&natureday="+natureday;
	var url=path+"/calendar/dateQuery.do";
	ajaxReq(data, url, sdate, "post");		
	
}

$('.datereset').click(function(){
	if($('#flag').attr("disabled")==null){
		$('#flag').attr("disabled",true);
		$('#datecs').attr("disabled",true);
		$("#opbutton").html("取消修改");
	    $('.fubg').css('display','none');
	}else{
		$('#flag').attr("disabled",false);
		$('#datecs').attr("disabled",false);
		$("#opbutton").html("日期类型修改");
	    $('.fubg').css('display','block');
	}

})
//弹出框弹出方法
function showmd(){
    $('.delewrap').modal('show');
}
