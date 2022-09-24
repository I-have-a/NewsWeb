<%--
  Created by IntelliJ IDEA.
  User: 23686
  Date: 2022/9/17
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册</title>
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
    <h1>注册
        <small>已有账号，<a href="login.jsp">登录</a></small>
    </h1>
    <form action="signup_Idea.jsp">
        <div class="form-group">
            <label for="phone">手机</label>
            <input type="text" name="phone" id="phone" class="form-control">
        </div>
        <div class="form-group">
            <label for="authCode">验证码</label>
            <div class="input-group">
                <input type="text" name="authCode" id="authCode" class="form-control">
                <div class="input-group-btn">
                    <div class="btn btn-default">获取验证码</div>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label for="password">密码</label>
            <input type="password" name="password" id="password" class="form-control">
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block">注册</button>
        </div>
        <div class="form-group">
            注册极客开发者头条即代表您同意<a href="#">极客开发者服务条款</a>
        </div>
    </form>
</div>
<jsp:include page="common/tail.jsp" flush="true"></jsp:include>
</body>
</html>