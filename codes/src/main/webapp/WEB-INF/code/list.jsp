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
        .page{
            margin: 0 auto;
        }
    </style>
</head>
<body>
<%@include file="../header.jsp" %>
<div class="container center-block">
    <div class="main-left col-sm-8 hidden-xs">
        <div id="dataList"> </div>
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
<script id="listTemplate" type="text/x-jquery-tmpl">
<div class="record clearfix" style="position:relative">
    <div class="f1 code-main clearfix">
        <div class="tittle">@{title}</div>
        <div class="code">
            <pre>@{code}</pre>
            <a class="toggle-code"></a>
        </div>
    </div>
    <div class="f1 value">@{value}</div>
    <div class="language">@{language}</div>
    <div class="tag">
        {{each tags}}
        <span class="label label-info">@{@value}</span>
        {{/each}}
    </div>
    <div class="user">用户</div>
</div>
</script>
<script>
    var pageSize = 5;
    var option = {
        dataDiv: $("#dataList"),
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
        ME.loadData(option);
    });


</script>
</body>
</html>