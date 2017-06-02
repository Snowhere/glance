/*! dataGrid整合 Ajax请求,laypage分页组件和数据展示功能.By snowhere */
;!function (w) {
    var ME = {} || ME;
    var DataGrid;
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
                $(".datagrid").remove();
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
                    for (var i = 0; i < msg.list.length; i++) {
                        var temp = option.dataDiv.clone();
                        for (var j = 0; j < option.rules.length; j++) {
                            var value = msg.list[i][option.rules[j].name];
                            if (option.rules[j].formatter) {
                                value = option.rules[j].formatter(i, msg.list[i]);
                            }
                            //填充value
                            $(temp.children("li").get(j)).html(value);
                        }
                        temp.css("display", "block");
                        temp.addClass("datagrid");
                        temp.appendTo(option.dataDiv.parent());
                    }
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
    w.ME.DataGrid = DataGrid;
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