<%--
  Created by IntelliJ IDEA.
  User: 23686
  Date: 2022/9/17
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登陆</title>
    <link rel="stylesheet" href="./css/bootstrap.min.css">
    <link rel="stylesheet" href="./css/main.css">
</head>
<body>
<div class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <a href="index.jsp" class="navbar-brand"></a>
        </div>
        <label for="toggle-checkbox" id="toggle-label" class="visible-xs-inline-block">菜单</label>
        <input type="checkbox" class="hidden" id="toggle-checkbox">
        <div class="hidden-xs">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">首页</a></li>
                <li><a href="#">国内</a></li>
                <li><a href="#">国际</a></li>
                <li><a href="#">数读</a></li>
                <li><a href="#">社会</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="login.jsp">登陆</a></li>
                <li><a href="signup.jsp">注册</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="container container-small">
    <h1>登录
        <small>没有账号？<a href="signup.jsp">注册</a></small>
    </h1>
    <form action="login_Idea.jsp" method="post">
        <div class="form-group">
            <label for="account">用户名/手机/邮箱</label>
            <input type="text" name="account" id="account" class="form-control">
        </div>
        <div class="form-group">
            <label for="password">密码</label>
            <input type="password" name="password" id="password" class="form-control">
        </div>
        <% String err = (String) request.getAttribute("err");%>
        <p><%= err != null ? err : ""%></p>
        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block">登录</button>
        </div>
        <div class="form-group">
            <a href="#">忘记密码？</a>
        </div>
    </form>
</div>
<jsp:include page="common/tail.jsp" flush="true"></jsp:include>
</body>
</html>
