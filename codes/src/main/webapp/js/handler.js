/**
 * Created by Administrator on 2017/6/2.
 */
;!function (w) {
    var ME = {} || ME;
    var Handler = {};
    Handler.dealError = function (response) {
        //TODO alert(response.msg);
        switch (response.code) {
            case 1:return true;
            default:return false;
        }
    };
    w.ME = ME;
    w.ME.Handler = Handler;
}(window);