<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- jquery-->
<script src="https://cdn.bootcss.com/jquery/1.12.1/jquery.min.js"></script>

<!-- layer-->
<script src="https://cdn.bootcss.com/layer/3.0.1/layer.js"></script>
<script src="/js/laypage/laypage.js"></script>

<!-- Bootstrap-->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<!-- others -->
<script src="/js/jquery.tmpl.min.js"></script>
<!-- mine-->
<script src="/js/datagrid.js"></script>
<script src="/js/handler.js"></script>
<script>
    $.ajaxSetup({
        error: function () {
            layer.alert('/(ㄒoㄒ)/~~哎呀，服务器有问题了，请刷新后再试试');
            if(changeCaptcha) {
                changeCaptcha();
            }
        }
    });
</script>
