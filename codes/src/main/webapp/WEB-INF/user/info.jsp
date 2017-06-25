<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../css.jsp" %>
</head>
<body>
<%@include file="../header.jsp" %>


<div class="container center-block">
    <div class="col-sm-offset-1 col-sm-3 hidden-xs" style="margin-top:50px;text-align: center;">
        <img src="${user.avatar}"/>
        <br>
        <p>${user.name}</p>


    </div>
    <div class="col-sm-8">
        <ul class="nav nav-tabs">
            <li role="presentation" name="0" id="person" class="tab active"><a href="#person">个人信息</a></li>
            <li role="presentation" name="1" id="bind" class="tab"><a href="#bind">绑定信息</a></li>
            <li role="presentation" name="2" id="contribute" class="tab"><a href="#contribute">个人贡献</a></li>
        </ul>
        <div class="content">
            1
            <button>修改</button>
            <button>提交</button>
        </div>
        <div class="content" style="display: none">
            2
        </div>
        <div class="content" style="display: none">
            3
        </div>

    </div>

</div>
<%@include file="../footer.jsp" %>
<%@include file="../js.jsp" %>
<script>
    function toggleTab(tab) {
        $('.tab').each(function () {
            $(this).removeClass('active');
        });
        $('.content').each(function () {
            $(this).hide();
        });
        tab.addClass('active');
        $($('.content')[tab.attr('name')]).show();
    }
    $('.tab').click(function () {
        toggleTab($(this));
    });
    $(document).ready(function() {
        var hash =location.hash;
        if(hash) {
            toggleTab($(hash));
        }
    })
</script>
</body>
</html>
