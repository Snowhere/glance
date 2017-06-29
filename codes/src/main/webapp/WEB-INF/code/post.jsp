<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../css.jsp" %>
    <style>
        .main-left {
            background-color: white;
        }
        form {
            padding: 20px;
        }
    </style>
</head>
<body>
<%@include file="../header.jsp" %>
<div class="container center-block">
    <div class="main-left col-sm-8 hidden-xs">
        <form id="submitForm">
            <div class="form-inline">
                <select name="language" class="form-control">
                    <c:forEach items="${languages}" varStatus="i" var="language">
                        <option value="${language}">${language}</option>
                    </c:forEach>
                </select>
                <input class="form-control" style="width:60%" type="text" placeholder="标签，用英文逗号分隔">
            </div>
            <textarea name="" class="form-control" rows="4" placeholder="依赖包或者库"></textarea>
            <textarea name="code" class="form-control" rows="22" placeholder="代码主体"></textarea>
            <textarea name="" class="form-control" rows="4" placeholder="描述"></textarea>
            <textarea name="" class="form-control" rows="4" placeholder="注意"></textarea>
            <textarea name="" class="form-control" rows="4" placeholder="其他"></textarea>
            <div class="btn-group" style="display: table; width: auto;margin:10px auto;" >
                <button type="submit" style="margin: 10px auto" class="btn btn-default">提交</button>
            </div>
        </form>
    </div>

    <div class="main-right col-sm-offset-1 col-sm-3 hidden-xs">
        <div class="sider">
            <c:forEach items="${topUsers}" varStatus="i" var="user">

            </c:forEach>
            <c:forEach items="${topCodes}" varStatus="i" var="code">

            </c:forEach>
            <div class="clearfix">
                <a style="float:left;width:30%" href="#"><img class="img-responsive" src="https://www.gravatar.com/avatar/2db2c4c244f8093953787d3d246b4d17"></a>
                <p>用户名</p>
                <p>贡献：123</p>
            </div>
            <div class="clearfix">
                <a style="float:left;width:30%" href="#"><img class="img-responsive" src="https://www.gravatar.com/avatar/2db2c4c244f8093953787d3d246b4d17"></a>
                <p>用户名</p>
                <p>贡献：123</p>
            </div>
        </div>

        <c:forEach items="${ads}" varStatus="i" var="ad">
            <a class="sider" href="${ad.link}"><img src="${ad.img}" class="img-responsive" alt="${ad.alt}"></a>
        </c:forEach>
    </div>

   <%-- <jsp:include page="../sider.jsp"/>--%>
</div>

<%@include file="../footer.jsp" %>
<%@include file="../js.jsp" %>
</body>
</html>
