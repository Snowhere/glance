<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../css.jsp" %>
</head>
<body>
<%@include file="../header.jsp" %>


<div class="container center-block">
    <div class="col-sm-offset-1 col-sm-3 hidden-xs" style="text-align: center;">
        <img src="${user.avatar}"/>
        <br>
        <p>${user.name}</p>


    </div>
    <div class="col-sm-8">
        <ul class="nav nav-tabs">
            <li role="presentation" class="tab active"><a href="#person">个人信息</a></li>
            <li role="presentation" class="tab"><a href="#bind">绑定信息</a></li>
            <li role="presentation" class="tab"><a href="#contribute">个人贡献</a></li>
        </ul>
        <div>
            123456788
        </div>
    </div>

</div>
<%@include file="../js.jsp" %>
<script>
    function toggleTab(tab) {
        $('.tab').each(function () {
            $(this).removeClass('active');
        });
        tab.addClass('active');
    }
    $('.tab').click(function () {
        toggleTab($(this));
    });
</script>
</body>
</html>
