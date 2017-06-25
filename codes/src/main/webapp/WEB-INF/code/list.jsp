<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../css.jsp" %>
    <style>
        .f1 {
            float: left;
        }

        .code-main {
            width: 90%;
        }

        .main-left {
            background-color: white;
        }

        .record {
            padding: 20px;

        }

        pre {
            height: 200px;
        }

        .language {
            background-color: blue;
            color: white;
            display: block;
            height: 22px;
            line-height: 22px;
            font-size: 12px;
            padding: 0 5px;
            position: absolute;
            right: 0;
            top: 0;
        }

        .value {
            margin: 50px 5px;
            text-align: center;
        }

        .tag {
            width: 100%;
            float: left;
        }

        .user {
            float: left;
        }
    </style>
</head>
<body>
<%@include file="../header.jsp" %>
<div class="container center-block">
    <div class="main-left col-sm-8 hidden-xs">
        <div class="list">
            <div class="record clearfix" data-array="list" style="position:relative;display: none">
                <div class="f1 code-main clearfix">
                    <div class="tittle" data-name="title">我是标题</div>
                    <div class="code">
                    <pre data-name="code"></pre><a class="toggle-code"></a>
                    </div>
                </div>
                <div class="f1 value" data-name="value"></div>
                <div class="language" data-name="language"></div>
                <div class="tag">
                    <span class="label label-info" data-array="tags" data-iter="tag">
                        <span data-name="tag"></span>
                    </span>
                </div>
                <div class="user">用户</div>
            </div>
        </div>
        <div class="page"></div>
    </div>
    <div class="main-right col-sm-offset-1 col-sm-3 hidden-xs">
        11111111111
    </div>
    <div class="main-right col-sm-offset-1 col-sm-3 hidden-xs">
        222222
    </div>
</div>
<%@include file="../footer.jsp" %>
<%@include file="../js.jsp" %>
<script>
    var pageSize = 5;
    var option = {
        dataDiv: $(".list"),
        page: {
            cont: $(".page"), // 容器。值支持id名、原生dom对象，jquery对象,
            skip: true,
            skin: "#f09",
            groups: 5, // 连续显示分页数
            hash: "page", // 分页hash值
        },
        url: "/code/codeSearch",
        para: {
            pageSize:pageSize
        },
        successFun: function (msg) {

        },
        failFun: function () {

        },
        noDataFun: function () {

        }
    }

    $(document).ready(function () {
        var hash =location.hash;
        var page = hash.split('=')[1]||1;
        option.para.pageNum = page;
        ME.DataGrid.loadData(option);
    });


</script>
</body>
</html>