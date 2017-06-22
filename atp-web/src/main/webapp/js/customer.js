/**
 * Created by jason on 2016/5/11.
 */
//开户行名称搜索弹窗
function cusbank(){
    var divf = $('.customer ul.dema li .banksh .bankwp');
    $(function (){
        $(".customer ul.dema li .banksh span.seabtn").click(function (event){
            //显示弹窗
            $(this).siblings('.bankwp').fadeIn();
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
cusbank();


function cusbankb(){
    var diva = $('.panel .screen ul.shaix li .bankwp');
    $(function (){
        $(".panel .screen ul.shaix li span.seabtn").click(function (event){
            //显示弹窗
            $(this).siblings('.bankwp').fadeIn();
            //$(this).parent('li').siblings().children('.bankwp').fadeIn();
            $(document).one("click", function (){
                //点击其他区域隐藏
                //alert(1);
                diva.fadeOut();
            });
            event.stopPropagation();
        });
        $(diva).click(function (event){
            event.stopPropagation();
        });
    });
}
cusbankb();


//双击弹出
$('.fund-details .detailwrap .customer ul.dema li .banksh .bankwp .banktable table tr').dblclick(function(){
    alert($(this).index());
    $('.customer ul.dema li .banksh .bankwp').fadeOut();
})

$('.fund-details .detailwrap .customer ul.dema li .banksh .kewrap .sresult .retablewrap table tr').dblclick(function(){
    alert($(this).index());
    $('.customer ul.dema li .banksh .bankwp').fadeOut();

})


$('.fund-details .detailwrap .customer ul.dema li .banksh .kewrap .leix .leix-right label').click(function(){
    var invalue=$(this).children('input').val();
    //alert(aa);
    $('.fund-details .detailwrap .customer ul.dema li .banksh .kewrap .seawrap p span').html(invalue+'：');
    $('.fund-details .detailwrap .customer ul.dema li .banksh .kewrap .seawrap p input').attr('placeholder','搜索'+invalue);
})

$('.panel .screen ul.shaix li .kewrap .leix .leix-right label').click(function(){
    var invaluea=$(this).children('input').val();
    //alert(aa);
    $('.panel .screen ul.shaix li .kewrap .seawrap p span').html(invaluea+'：');
    $('.panel .screen ul.shaix li .kewrap .seawrap p input').attr('placeholder','搜索'+invaluea);
})

$('.panel .screen ul.shaix li .kewrap .sresult .retablewrap table tr').dblclick(function(){
    alert($(this).index());
    $('.panel .screen ul.shaix li .bankwp').fadeOut();
})

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
        $('.daicatb').html(arr[x]+'：');
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