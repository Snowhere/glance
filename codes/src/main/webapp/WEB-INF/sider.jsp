<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="main-right col-sm-offset-1 col-sm-3 hidden-xs">
    <div class="sider">
        <c:forEach items="${topUsers}" varStatus="i" var="user">
            {
        </c:forEach>
        <c:forEach items="${topCodes}" varStatus="i" var="code">

        </c:forEach>
    </div>

    <c:forEach items="${ads}" varStatus="i" var="ad">
        <a class="sider" href="${ad.link}"><img src="${ad.img}" class="img-responsive" alt="${ad.alt}"></a>
    </c:forEach>
</div>