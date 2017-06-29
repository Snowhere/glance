/*! dataGrid整合 Ajax请求,laypage分页组件和数据展示功能.By snowhere */
;!function (w) {
    var me = w.ME ||{};

    me.loadData = function (option) {
        layer.load(1, {
            shade: [0.8, "#DDDDDD"],
            time: 300
        });
        $.ajax({
            type: "post",
            url: option.url,
            data: option.para,
            dataType: "json",
            success: function (msg) {
                layer.closeAll("loading");
                var obj = msg.obj;
                option.dataDiv.empty();
                if (obj.list && obj.list.length) {
                    /*
                     *有数据
                     */
                    //分页页码
                    var pageOption = {
                        pages: obj.totalPage, // 通过后台拿到的总页数
                        curr: option.para.pageNum || 1,
                        // 获取hash值为fenye的当前页
                        hash: "page", // 自定义hash值
                        jump: function (obj, first) {
                            if (!first) { // 点击跳页触发函数自身，并传递当前页：obj.curr
                                option.para.pageNum = obj.curr;
                                me.loadData(option);
                            }
                        }
                    }
                    laypage($.extend(pageOption, option.page));
                    //数据填充
                    $('#listTemplate').tpl(obj.list).appendTo('#dataList');
                    option.successFun(obj);
                } else {
                    /*
                     *无数据
                     */
                    option.noDataFun();
                }
            },
            error: function () {
                layer.closeAll("loading");
                option.failFun();
            }
        });
    }
    w.ME = me;
}(window);
/* 
 * option格式
 */
/*
var option = {
    dataDiv: $(".list"),
    page: {
        cont: $(".page"), // 容器。值支持id名、原生dom对象，jquery对象,
        skip: true,
        skin: "#f09",
        groups: 5, // 连续显示分页数
        // 获取hash值为fenye的当前页
        hash: "page", // 自定义hash值
    },
    url: pathUrl + "store/storeList",
    para: {},
    successFun:function(msg){
    	$("#sum i").text(msg.totalRow);
    	$("#listTitle,.paging").show();
    	$("#noDataMsg").hide();
    },
    failFun: function() {
        window.location.reload();
    },
    noDataFun: function() {
        $("#listTitle,.paging").hide();
        $("#sum i").text("0");
        $("#noDataMsg").show();
    }
}*/