/**
 * Created by Administrator on 2017/6/2.
 */
;!function (w) {
    var me = w.ME ||{};
    var errorMap = [];

    me.dealError = function (response) {
        if (errorMap[response.code]) {
            errorMap[response.code](response)
        }else {
            layer.alert(response.msg);
        }
    };
    me.valid=function(dom,value,valid) {
        var ico = dom.find('span')
        if (valid(value)) {
            dom.removeClass('has-error');
            dom.addClass('has-success');
            ico.removeClass('glyphicon-remove');
            ico.addClass('glyphicon-ok');
            return true;
        } else {
            dom.removeClass('has-success');
            dom.addClass('has-error');
            ico.removeClass('glyphicon-ok');
            ico.addClass('glyphicon-remove');
            return false;
        }
    }
    w.ME = me;
}(window);