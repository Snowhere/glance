/**
 * Created by Administrator on 2017/6/2.
 */
;!function (w) {
    var ME = {} || ME;
    var Handler = {};
    Handler.errorMap = [];


    Handler.dealError = function (response) {
        if (Handler.errorMap[response.code]) {
            Handler.errorMap[response.code](response)
        }else {
            layer.alert(response.msg);
        }
    };
    w.ME = ME;
    w.ME.Handler = Handler;
}(window);