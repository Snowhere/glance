/*! dataGrid整合 Ajax请求,laypage分页组件和数据展示功能.By snowhere */
;!function (w) {
    var me = w.ME ||{};
    var DataGrid = {};
    /**
     * 1.object，递归解析
     * 2.list，遍历
     * 3.其他情况直接填充
     * @param name 名称路径 data-array & data-iter , data-obj , data-name
     * @param data 数据
     * @param view 注入数据的jquery对象
     */
    var fill = function(name,data,view) {
        //数组
        if ($.isArray(data)) {
            //找到数组模板,获取迭代名称
            var arrayView = view.find('[data-array=' + name + ']');
            var arrayName = arrayView.attr('data-iter');
            $.each(data, function (index, value) {
                var temp = arrayView.clone();
                temp.attr('data-view', 'data');
                temp.appendTo(arrayView.parent());
                temp.show();
                fill(arrayName, value, temp);
            });
        }
        //对象
        else if (typeof data == 'object' && data != null) {
            $.each(data, function (key, value) {
                fill(key, value, view);
            });
        }
        //其他
        else {
            var dataView = view.find('[data-name=' + name + ']');
            dataView.html(data);
        }
    }
    DataGrid.loadData = function (option) {
        layer.load(1, {
            shade: [0.8, "#393D49"],
            time: 300
        });
        $.ajax({
            type: "post",
            url: option.url,
            data: option.para,
            dataType: "json",
            success: function (msg) {
                layer.closeAll("loading");
                $('[data-view="data"]').remove();
                var msg = msg.obj;
                if (msg.list && msg.list.length) {
                    /*
                     *有数据
                     */
                    //分页页码
                    var pageOption = {
                        pages: msg.totalPage, // 通过后台拿到的总页数
                        curr: option.para.pageNum || 1,
                        // 获取hash值为fenye的当前页
                        hash: "page", // 自定义hash值
                        jump: function (obj, first) {
                            if (!first) { // 点击跳页触发函数自身，并传递当前页：obj.curr
                                option.para.pageNum = obj.curr;
                                DataGrid.loadData(option);
                            }
                        }
                    }
                    laypage($.extend(pageOption, option.page));
                    //数据填充
                    option.dataDiv.find('[data],[data-array]').hide();
                    fill('list', msg.list, option.dataDiv);
                    option.successFun(msg);
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
    me.DataGrid = DataGrid;

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
    rules: [{
        name: "id",
        formatter: function(index, row) {
            return "<b style="display:none">" + row.id + "</b><a target="_blank" href="" + pathUrl + "store/storeInfo/" + row.id + ""><img src="" + row.img1 + "" width="120" height="90"></a>";
        }
    }, {
        name: "name",
        formatter: function(index, row) {
            return "<span><p><a target="_blank" href="" + pathUrl + "store/storeInfo/" + row.id + "">" + row.storeName + "</a></p><p><a target="_blank" href="" + pathUrl + "store/storeInfo/" + row.id + "">" + row.storeCode + "</a></p></span>"
        }
    }, {
        name: "buildingName"
    }, {
        name: "area"
    }, {
        name: "yearRent"
    }, {
        name: "day_rent_per_centare"
    }, {
        name: "date",
        formatter: function(index, row) {
            return "<span><p>" + row.date + "</p><p>" + row.time + "</p></span>";
        }
    }, {
        name: "advocateName",
        formatter: function(index, row) {
            var advocate = row.advocate;
            var advocateName = row.advocateName;
            if (advocate == 0) {
                if (row.delegateType != 2) {
                    return "<span class="noWeihu">维护</span>";
                } else {
                    return advocateName;
                }
            } else {
                if (advocate == userId) {
                    return "<span class="cur"><span>" + advocateName + "</span><i>放弃维护</i></span>";
                } else {
                    return advocateName;
                }
            }
        }
    }],
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