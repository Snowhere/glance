<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../css.jsp" %>
</head>
<body>
<%@include file="../header.jsp" %>

<div class="container center-block">
    <h2>登录</h2>
    <form id="submitForm" class="form-horizontal">
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
                <input type="password" required="required" class="form-control" id="password" placeholder="password">
                <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
            </div>
        </div>


        <div class="form-group">
            <label class="col-sm-3 control-label">验证码</label>
            <div class="col-sm-7">
                <a href="#" class="col-sm-2"><img id="captchaImg" src="/user/captcha"></img></a>
                <input required="required" class="form-control col-sm-3" id="captcha" placeholder="captcha">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-4 col-sm-4">
                <button type="submit" class="btn btn-default">Submit</button>
            </div>
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