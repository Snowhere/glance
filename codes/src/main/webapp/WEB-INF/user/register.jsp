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
        <div id="usernameDiv" class="form-group has-feedback">
            <label for="username" class="col-sm-3 control-label">用户名</label>
            <div class="col-sm-7">
                <input type="text" required="required" class="form-control" id="username"
                       placeholder="4-16位数字、字母、下划线，不能是纯数字或纯下划线">
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
        <div id="repeatDiv" class="form-group has-feedback">
            <label for="repeat" class="col-sm-3 control-label">确认密码</label>
            <div class="col-sm-7">
                <input type="password" required="required" class="form-control" id="repeat" placeholder="重复一遍密码">
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
<%@include file="../js.jsp" %>
<script>
    //校验
    function validUsername(value) {
        var format = /^[a-zA-Z0-9_]*$/.test(value);
        var length = value.length <= 17 && value.length > 3;
        var line = /^_+$/.test(value);
        var number = /^\\d+$/.test(value);
        return format && length && !line && !number;
    }
    function validPassword(value) {
        return value.length > 0;
    }
    function validRepeat(value) {
        return value == $('#password').val();
    }

    //换验证码
    function changeCaptcha() {
        $('#captchaImg')[0].src = '/user/captcha?' + new Date().getTime();
    }
    $('#username').blur(function () {
        ME.Handler.valid($('#usernameDiv'), $(this).val(), validUsername);
    });
    $('#password').blur(function () {
        ME.Handler.valid($('#passwordDiv'), $(this).val(), validPassword);
    });
    $('#repeat').blur(function () {
        ME.Handler.valid($('#repeatDiv'), $(this).val(), validRepeat);
    });

    //换验证码
    $('#captchaImg').click(function () {
        changeCaptcha();
    });
    //提交
    $('#submitForm').submit(function () {
        if (ME.Handler.valid($('#usernameDiv'), $('#username').val(), validUsername) && ME.Handler.valid($('#passwordDiv'), $('#password').val(), validPassword) && ME.Handler.valid($('#repeatDiv'), $('#repeat').val(), validRepeat)) {
            $.getJSON('/user/userRegister', {
                username: $('#username').val(),
                password: $('#password').val(),
                captcha: $('#captcha').val(),
                type: 'local'
            }, function (response) {
                if (response.success) {
                    window.location.href = '/';
                } else {
                    changeCaptcha();
                    ME.Handler.dealError(response);
                }
            });
        }
        return false;
    });
</script>
</body>
</html>
