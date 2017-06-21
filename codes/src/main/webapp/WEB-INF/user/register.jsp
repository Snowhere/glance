<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../css.jsp" %>
</head>
<body>
<%@include file="../header.jsp" %>

<div class="container center-block">
    <h2>注册</h2>
    <form id="submitForm" class="form-horizontal">
        <div class="form-group has-success has-feedback">
            <label for="username" class="col-sm-3 control-label">用户名(用于登录和展示)</label>
            <div class="col-sm-7">
                <input type="text" class="form-control" id="username" placeholder="4-16位数字和字母">
                <span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>
                <span class="sr-only">(success)</span>
            </div>
        </div>
        <div class="form-group has-success has-feedback">
            <label for="password" class="col-sm-3 control-label">密码</label>
            <div class="col-sm-7">
                <input type="password" class="form-control" id="password" placeholder="password">
                <span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>
                <span class="sr-only">(success)</span>
            </div>
        </div>
        <div class="form-group has-success has-feedback">
            <label for="repeat" class="col-sm-3 control-label">确认密码</label>
            <div class="col-sm-7">
                <input type="password" class="form-control" id="repeat" placeholder="repeat">
                <span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>
                <span class="sr-only">(success)</span>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">验证码</label>
            <div class="col-sm-7">
                <a href="#" class="col-sm-2"><img id="captchaImg" src="/user/captcha"></img></a>
                <input class="form-control col-sm-3" id="captcha" placeholder="captcha">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-4 col-sm-4">
                <button type="submit" class="btn btn-default">Submit</button>
            </div>
        </div>
    </form>
</div>
<%@include file="../js.jsp" %>
<script>
    //换验证码
    $('#captchaImg').click(function () {
        this.src = '/user/captcha?' + new Date().getTime();
    });
    //提交
    $('#submitForm').submit(function () {
        $.getJSON('/user/userRegister', {
            username:$('#username').val(),
            password:$('#password').val(),
            captcha:$('#captcha').val(),
            type:'local'
        }, function (response) {
            if(response.success) {
                window.location.href='/';
            }else {
                ME.Handler.dealError(response);
            }
        });
        return false;
    });
</script>
</body>
</html>
