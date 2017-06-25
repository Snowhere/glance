/**
 * Created by Administrator on 2017/6/2.
 */
var ME = (function (me) {
    var Handler = {};
    Handler.errorMap = [];


    Handler.dealError = function (response) {
        if (Handler.errorMap[response.code]) {
            Handler.errorMap[response.code](response)
        }else {
            layer.alert(response.msg);
        }
    };
    Handler.valid=function(dom,value,valid) {
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
    me.Handler = Handler;
})(ME||{});