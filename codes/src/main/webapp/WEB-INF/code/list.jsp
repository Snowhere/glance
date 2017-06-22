<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../css.jsp"%>
</head>
<body>
<%@include file="../header.jsp"%>
<div class="container center-block">
    <div class=" col-sm-8 hidden-xs" style="text-align: center;">
      11111111list

    </div>
    <div class="col-sm-4 hidden-xs">
       11111111111
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