<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../css.jsp" %>
</head>
<body>
<%@include file="../header.jsp" %>

<div class="container center-block">
    <form id="submitForm" class="form-horizontal col-sm-offset-2 col-sm-8">
        <div id="usernameDiv" class="form-group has-feedback">
            <label for="username" class="col-sm-3 control-label">用户名</label>
            <div class="col-sm-7">
                <input type="text" required="required" class="form-control" id="username"
                       placeholder="4-16位数字字母下划线，不能是纯数字或下划线">
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
                 <input required="required" class="form-control col-sm-3" id="captcha" placeholder="输入验证码">
                <a href="#" class="col-sm-2"><img id="captchaImg" src="/user/captcha"></img></a>

            </div>
        </div>
        <div class="btn-group" style="display: table; width: auto;margin:10px auto;">
            <button type="submit" class="btn btn-default">注册</button>
        </div>
    </form>
</div>
<%@include file="../footer.jsp" %>
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
        ME.valid($('#usernameDiv'), $(this).val(), validUsername);
    });
    $('#password').blur(function () {
        ME.valid($('#passwordDiv'), $(this).val(), validPassword);
    });
    $('#repeat').blur(function () {
        ME.valid($('#repeatDiv'), $(this).val(), validRepeat);
    });

    //换验证码
    $('#captchaImg').click(function () {
        changeCaptcha();
    });
    //提交
    $('#submitForm').submit(function () {
        if (ME.valid($('#usernameDiv'), $('#username').val(), validUsername) && ME.valid($('#passwordDiv'), $('#password').val(), validPassword) && ME.valid($('#repeatDiv'), $('#repeat').val(), validRepeat)) {
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
                    ME.dealError(response);
                }
            });
        }
        return false;
    });
</script>
</body>
</html>
