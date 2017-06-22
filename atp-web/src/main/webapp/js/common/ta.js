/**
 * Created by zongpeng on 16-5-3.
 */
function selectAllTa(obj){
    if($(obj).is(':checked')) {
        $("[name='tacheck']").prop('checked', true);
        $("[name='tacheck']").parent().parent().addClass("chos");
    } else {
        $("[name='tacheck']").prop('checked', false);
        $("[name='tacheck']").parent().parent().removeClass("chos");
    }
    getSelectTa();
}
function selectTa(obj) {
    if ($(obj).is(':checked')) {
        $(obj).parent().parent().addClass("chos");
        if ($("[name='tacheck']").not("input:checked").length==0) {
            $("#alltacheck").prop('checked', true);
            $("#alltacheck").parent().parent().addClass("chos");
        }
    } else {
        $(obj).parent().parent().removeClass("chos");
        $("#alltacheck").prop('checked', false);
        $("#alltacheck").parent().parent().removeClass("chos");
    }
    getSelectTa();
}

function getSelectTa() {
    var arr = new Array();
    $("[name='tacheck']").each(function(){
        if ($(this).is(':checked')) {
            arr.push($(this).val());
        }
    });
    var child = $('#duox').children();
    $('#duox').html(arr.join(',')).append(child);
}
function init() {
    getSelectTa();
    if ($("[name='tacheck']").not("input:checked").length==0) {
        $("#alltacheck").prop('checked', true);
        $("#alltacheck").parent().parent().addClass("chos");
    }
}