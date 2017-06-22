/**
 * Created by Windows on 2016/3/11.
 */

// table表格全选控制  全选
function checkboxa(){
    var chcks = $(" table tr td input[type='checkbox']");
    var state = $(" table tr th input[type='checkbox']");
    //$state.on('change', function(ev)
    state.parents('span').on('click',function()
    {
        if($(this).children('input').attr('checked'))
        {
            //alert(1);
            //chcks.prop('checked', false).trigger('change');
            chcks.removeAttr("checked");
            state.removeAttr("checked");
            state.parent('span').removeClass('chos');
            chcks.parent('span').removeClass('chos');
            chcks.parents('tr').removeClass('activea');

            //取消全选之后下列可选
            cho();
        }
        else
        {
            //alert(2);
            //chcks.prop('checked', true).trigger('change');
            chcks.attr("checked",'true');
            state.attr("checked",'true');
            state.parent('span').addClass('chos');
            chcks.parent('span').addClass('chos');
            chcks.parents('tr').addClass('activea');
            //全选之后下列不可选
            chcks.parent('span').unbind("click");
        }
    });
}
checkboxa();
//反选
function fanxuan(){
    $('.tablep table tr th button.fanxuan').click(function(){
        $("table tr td input[type='checkbox']").parent('span').unbind("click");
        cho();
        $(this).parents('tr').siblings('tr').children('td').children('span').children('input[type="checkbox"]').each(function(){
            if($(this).attr("checked"))
            {
                $(this).removeAttr("checked");
                $(this).parent('span').removeClass('chos');
                $(this).parents('tr').removeClass('activea');
                $("table tr th input[type='checkbox']").parent('span').removeClass('chos');
                //$(this).parents('tr').siblings('tr').children('th').children('label').children('span').removeClass('chos');
                $("table tr th input[type='checkbox']").removeAttr("checked");
                //$(this).parents('tr').siblings('tr').children('th').children('label').children('span').children('input').removeAttr("checked");
            }else{
                $(this).attr("checked",'true');
                $(this).parent('span').addClass('chos');
                $(this).parents('tr').addClass('activea');
            }
        });

    })

}
fanxuan();
//table单选控制
function cho(){
    var chcks = $(" table tr td input[type='checkbox']");
    //var $chcks = $(".table-responsive table tr td input[type='checkbox']");
    chcks.parent('span').on('click',function(){
        if($(this).children('input[type="checkbox"]').attr("checked"))
        {
            //alert('1');
            $(this).children('input[type="checkbox"]').removeAttr("checked");
            $(this).removeClass('chos');
            $(this).parents('tr').removeClass('activea');
        }
        else
        {
            $(this).children('input[type="checkbox"]').attr("checked",'true');
            $(this).addClass('chos');
            $(this).parents('tr').addClass('activea');

        }
    })
}
cho();




//商户组弹窗单选控制
function shos(){
    var shs = $(".modal .xinzm .xzmain .bwarp ul li .banklist .bkright label strong input[type='checkbox']");
    shs.parent('strong').on('click',function(){
        if($(this).children('input[type="checkbox"]').is(':checked'))
        {
            //alert('1');
            $(this).children('input[type="checkbox"]').prop('checked', false).trigger('change');
            $(this).parent('label').removeClass('chos');
            //$(this).parents('tr').removeClass('activea');
        }
        else
        {
            $(this).children('input[type="checkbox"]').prop('checked', true).trigger('change');
            $(this).parent('label').addClass('chos');
            //$(this).parents('tr').addClass('activea');

        }
    })
}
shos();

//下拉多选按钮效果样式
function chta(){
    var chck = $(".duoxuan .xuanz label strong input[type='checkbox']");
    chck.parent('strong').on('click',function(){
        //alert('1');
        if($(this).children('input[type="checkbox"]').is(':checked'))
        {
            //alert('1');
            $(this).children('input[type="checkbox"]').prop('checked', false).trigger('change');
            //$(this).parents('').removeClass('chos');
            $(this).parent('label').removeClass('chos');
        }
        else
        {
            //alert('2');
            $(this).children('input[type="checkbox"]').prop('checked', true).trigger('change');
            //$(this).addClass('chos');
            $(this).parent('label').addClass('chos');
        }
    })

}
chta();


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

