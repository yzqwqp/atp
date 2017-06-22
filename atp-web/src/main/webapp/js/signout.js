/**
 * Created by Windows on 2016/7/4.
 */
$(document).ready(function(){
    var str="<div class='modal fade tout logoutdiv' tabindex='-1' role='dialog' aria-labelledby='myModalLabel' data-backdrop='static'><div class='modal-dialog' role='document' ><div class='modal-content'><div class='modal-header'><h4 class='modal-title'>信息提示</h4></div><div class='modal-body'><div class='xints'><h4>是否确认退出登录？</h4></div></div><div class='modal-footer'><p><button type='button' class='save' onclick='logoutdo()'>确认</button><button type='button' class='closebtn' data-dismiss='modal'>取消</button></p></div></div></div></div>";
    $("body").append(str);
});