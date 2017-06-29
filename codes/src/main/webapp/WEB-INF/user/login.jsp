<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../css.jsp" %>
</head>
<body>
<%@include file="../header.jsp" %>

<div class="container center-block">
    <form id="submitForm" class="form-horizontal col-sm-offset-3 col-sm-6">
        <div id="usernameDiv" class="form-group has-feedback">
            <label for="username" class="col-sm-3 control-label">用户名</label>
            <div class="col-sm-7">
                <input type="text" required="required" class="form-control" id="username" placeholder="用户名/邮箱/手机号">
                <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
            </div>
        </div>
        <div id="passwordDiv" class="form-group has-feedback">
            <label for="password" class="col-sm-3 control-label">密码</label>
            <div class="col-sm-7">
                <input type="password" required="required" class="form-control" id="password" placeholder="输入密码">
                <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
            </div>
        </div>


        <div class="form-group">
            <label class="col-sm-3 control-label">验证码</label>
            <div class="col-sm-7">

                <input required="required" class="form-control col-sm-3" id="captcha" placeholder="输入验证码">
                <a href="#" class="col-sm-2"><img id="captchaImg" src="/user/captcha"></img></a>
            </div>
        </div>
        <div class="btn-group" style="display: table; width: auto;margin:10px auto;">
            <button type="submit" class="btn btn-default">登录</button>
        </div>
    </form>
</div>
<%@include file="../footer.jsp" %>
<%@include file="../js.jsp" %>
<script>
    //换验证码
    $('#captchaImg').click(function () {
        this.src = '/user/captcha?' + new Date().getTime();
    });
    $('#submitForm').submit(function () {
        $.getJSON('/user/userLogin', {
            username: $('#username').val(),
            password: $('#password').val(),
            captcha: $('#captcha').val(),
            type: 'local'
        }, function (response) {
            if (response.success) {
                window.location.href = '/';
            } else {
                ME.dealError(response);
            }
        });
        return false;
    });
</script>
</body>
</html>