/**
 * Created by Windows on 2016/3/11.
 */
var chcks = $(".table-responsive table tr td input[type='checkbox']");
// table表格全选控制  全选
function checkboxa(){
    var state = $(".table-responsive table tr th input[type='checkbox']");
    //$state.on('change', function(ev)
    state.parent('span').on('click',function()
    {
        if(state.is(':checked'))
        {
            //alert('1');
            chcks.prop('checked', true).trigger('change');
            state.parent('span').addClass('chos');
            chcks.parent('span').addClass('chos');
            chcks.parents('tr').addClass('activea');
            chcks.parent('span').unbind("click");
        }
        else
        {
            chcks.prop('checked', false).trigger('change');
            state.parent('span').removeClass('chos');
            chcks.parent('span').removeClass('chos');
            chcks.parents('tr').removeClass('activea');
            cho();
        }
    });
}
checkboxa();
//单选控制
function cho(){
    //var $chcks = $(".table-responsive table tr td input[type='checkbox']");
    chcks.parent('span').on('click',function(){
        if($(this).children('input[type="checkbox"]').is(':checked'))
        {
            //alert('1');
            $(this).children('input[type="checkbox"]').prop('checked', false).trigger('change');
            $(this).removeClass('chos');
            $(this).parents('tr').removeClass('activea');
        }
        else
        {
            $(this).children('input[type="checkbox"]').prop('checked', true).trigger('change');
            $(this).addClass('chos');
            $(this).parents('tr').addClass('activea');

        }
    })
}
cho();

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

//多选显示隐藏
/*function aa(){
    var oLink=document.getElementById('duox');
    var oBox=document.getElementById('xzbox');
    oLink.onclick=function(ev)
    {
        var ev = ev || window.event;
        oBox.style.display='block';
        ev.cancelBubble=true;
    };
    oBox.onclick=function(ev)
    {
        var ev = ev || window.event;
        ev.cancelBubble=true;
    };
    document.onclick=function()
    {
        oBox.style.display='none';
    };
}
aa();*/

//多选下拉弹窗效果
function shde(){
	var myDiva = $(".xuanza");
    var myDivb = $(".xuanzb");
    $(function (){
        $(".dxbtna").click(function (event){
            //showDiva();
            $(this).next('.xuanz').fadeIn();
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

