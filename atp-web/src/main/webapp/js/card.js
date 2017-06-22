function fillcustinfo(json){
    if(json.statusCode=="0000"){
        var data=json.model;
        $('.daicat').html('<strong>*</strong>客户号：');
        $('#ctest').attr("column","custno");
        writeCustTable(data);
        $('#ctest').val(data.custno);
        $("#custno").val(data.custno);
        var invtp=data.invtp;
        if(invtp=='0'){
            $("#showDiv").show();
            $("#depositname").val("").removeAttr("readonly");
        }else{
            $("#showDiv").hide();
            $("#depositname").val(data.custname).attr("readonly","readonly");
        }
        var plats=data.acctPlatformInfos;
        if(plats==null){
            return;
        }
        var options="";
        var flag=false;
        for(var i=0;i<plats.length;i++){
            var item=plats[i];
            var status=item.status;
            if(status=="0"){
                flag=true;
                options+=("<option value='"+item.merchantno+"'>"+item.merchantname+"</option>");
            }
        }
        if(flag){
            $("#merchantno").html(options);
        }
    }else{
        alert(json.errMsg);
    }
    choiseMerchantno();
}

function fillCallBack(json) {
    clearmsg();
    $("#agentidtype").html("");
    $("#merchantno").html("");
    $("#license").val("");
    $("#explication").val("");
    $("#agentname").html("");
    $("#agentidcode").html("");
    if(json.statusCode=="0000"){
        var data=json.model;
        if(data.length>0){
            callWrite(data[0].custno);
        }
    }
}

function openDiv(){
    var chk=checkByHJ("personForm");
    if(!chk){
        return;
    }
    $(".dele").modal("show");
}

function doCall(json){
    $(".xinxi").modal("show");
    var model=json.model;
    $("#a0").html(model.msg1);
    $("#a1").html(model.msg2);
    $("#okbtn").unbind("click").click(function(){
        if(json.statusCode=="0000"){
            resetForm();
        }
        $('.xinxi').modal('hide');
    });
}

function choiseMerchantno(){
    var data={};
    data.merchantno=$("#merchantno").val();
    data.custno=$("#custno").val();
    if($("#merchantno").val()==""){
        callWrite($("#custno").val());
    }else{
        $("#merchantname").val($("#merchantno").find("option:selected").text());
        var url=path+"/acct/personopen/getplatform.do"
        ajaxReq(data, url, merchantCall, "post");
    }
    var url=path+"/acct/transclose/getBasic.do";
    ajaxReq(data, url, writeByMerchant, "post");
}

//开户行名称搜索弹窗
function custbank(){
    var divf = $('#bankwindow');
    $(function (){
        $("#bankquerybutton").click(function (event){
            //显示弹窗
            divf.fadeIn();
            $(document).one("click", function (){
                //点击其他区域隐藏
                divf.fadeOut();
            });
            event.stopPropagation();
        });
        $(divf).click(function (event){
            event.stopPropagation();
        });
    });
}
custbank();

//客户搜索弹窗
function cust(){
    var divf = $('#custwindow');
    $(function (){
        $("#custquerybutton").click(function (event){
            //显示弹窗
            divf.fadeIn();
            $(document).one("click", function (){
                //点击其他区域隐藏
                divf.fadeOut();
            });
            event.stopPropagation();
        });
        $(divf).click(function (event){
            event.stopPropagation();
        });
    });
}
cust();

$('#custs tr').dblclick(function(){
    $('#custwindow').fadeOut();

});

//F4按键切换条件
function keyfun(){
    var arr=new Array("客户代码","客户全称","基金帐号","证件号码","手机号码","银行帐号","交易帐号");//设置条件
    var arrcolumn=new Array("custno","custname","taaccountno","certificateno","mobileno","depositacct","transactionaccountid");//设置条件
    var i=0;
    function Fun(){
        //var oIpt=$('#ctest');
        //var oTitle=$('.daicat');
        console.log(i%7);
        i++;
        var x=i%7;
        //修改span标签内容
        $('.daicat').html('<strong>'+'*'+'</strong>'+arr[x]+'：');
        $('#ctest').attr("column",arrcolumn[x]);
        //传值到隐藏Input
        $('.hdinput').val(arr[x]);
    }
    //按键触发
    document.onkeyup = function (event) {
        var e = event || window.event;
        var keyCode = e.keyCode || e.which;
        if(keyCode==115){
            //alert("你按了F4键");
            Fun();
        }
    }
}
keyfun();

function bankCall(json){
    var trs="";
    for(var i=0;i<json.length;i++){
        var item=json[i];
        var bankno=item.bankno;
        var bankbranchcode=item.bankbranchcode;
        var bankname=item.bankname;
        var bankbranchname=item.bankbranchname;
        var depositprov=item.depositprov;
        var depositcity=item.depositcity;
        var bankshortname=item.bankshortname;
        var banknetno=item.banknetno;
        trs+="<tr ondblclick='callWritebank(this)' bankno='"+bankno+"' bankbranchcode='"+bankbranchcode+"' bankname='"+bankname+"' bankbranchname='"+bankbranchname+"' depositprov='"+depositprov+"' depositcity='"+depositcity+"' bankshortname='"+bankshortname+"' banknetno='"+banknetno+"'><td>"+bankbranchcode+"</td><td>"+bankbranchname+"</td></tr>";
    }
    $("#banks").html(trs);
}
function callWritebank(event){
    var bankno=$(event).attr("bankno");
    var bankbranchcode=$(event).attr("bankbranchcode");
    var bankname=$(event).attr("bankname");
    var bankbranchname=$(event).attr("bankbranchname");
    var depositprov=$(event).attr("depositprov");
    var depositcity=$(event).attr("depositcity");
    var bankshortname=$(event).attr("bankshortname");
    var banknetno=$(event).attr("banknetno");
    $("#bankno").val(bankno);
    $("#bankbranchcode").val(bankbranchcode);
    $("#banknamea").val(bankname);
    $("#bankbranchname").val(bankbranchname);
    $("#depositprov").val(depositprov);
    $("#depositcity").val(depositcity);
    $("#bankshortname").val(bankshortname);
    $("#banknetnoa").val(banknetno);
    $('#bankwindow').fadeOut();
}

function writeByMerchant(json){
    if(json.statusCode=="0000"){
        var data=json.model;
        var agent=data.acctAgentInfo;
        if(agent!=null){
            $("#agentidcode").html(agent.agentidcode);
            var type=agent.agentidtype;
            if(type=="0"){
                $("#agentidtype").html("身份证");
            }else if(type=="1"){
                $("#agentidtype").html("护照");
            }else if(type=="2"){
                $("#agentidtype").html("军官证");
            }else if(type=="3"){
                $("#agentidtype").html("士兵证");
            }else if(type=="4"){
                $("#agentidtype").html("港澳居民来往内地通行证");
            }else if(type=="5"){
                $("#agentidtype").html("户口本");
            }else if(type=="6"){
                $("#agentidtype").html("外国护照");
            }else if(type=="7"){
                $("#agentidtype").html("其它");
            }
            $("#agentname").html(agent.agentname);
            $("#agentidtypecode").val(agent.agentidtype);
        }
        var tanos=data.taBasicVos;
        var taoption="<option value=''>-请选择-</option>";
        if(tanos==null){
            $("#tano").html(taoption);
            return;
        }
        for(var i=0;i<tanos.length;i++){
            var item=tanos[i];
            taoption+=("<option value='"+item.tano+"'>"+item.tano+" "+item.orgname+"</option>");
        }
        $("#tano").html(taoption);
    }
}