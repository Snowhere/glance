<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container center-block">
        <div class="navbar-left col-sm-6">
            <a class="navbar-brand" href="#">
                <img alt="Brand" src="...">
            </a>
            <form class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="关键字">
                </div>
                <button type="submit" class="btn btn-default">Search</button>
            </form>
            <button type="button" class="btn btn-default navbar-btn">提交代码</button>
        </div>
        <div class="navbar-right col-sm-4">
            <c:if test="${user!=null}">
                <a class="navbar-brand" href="/user/${user.id}">
                    <img alt="头像" src="${user.avatar}">
                </a>
                <p class="navbar-text">${user.name}</p>
                <a href="/user/${user.id}" class="navbar-link">个人中心</a>
                <a href="/user/logout" type="button" class="btn btn-default navbar-btn">登出</a>
            </c:if>
            <c:if test="${user==null}">
                <a href="/user/login" type="button" class="btn btn-default navbar-btn">登录</a>
                <a href="/user/register" type="button" class="btn btn-default navbar-btn">注册</a>
            </c:if>
        </div>
    </div>
</nav>
