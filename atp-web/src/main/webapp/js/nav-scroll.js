/**
 * Created by xiaocheng on 2016/3/10.
 */
function run(){
    //右侧顶部流程步骤滚动
    var lengst=$('.topnav ul.step li').length;
    //alert(lengst);
    $('#scroller').css('width',(lengst*120+40)+'px');
    var myScroll;
    function loaded () {
        myScroll = new IScroll('#wrappera', { eventPassthrough: true,scrollbars: true, scrollX: true, scrollY: false, preventDefault: false });
    }
    loaded();
}
run();
