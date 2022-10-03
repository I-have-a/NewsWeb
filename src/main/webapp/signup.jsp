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
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
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
        <small>已有账号，<a href="logout.jsp">登录</a></small>
    </h1>
    <form action="signupServlet" method="post">
        <div class="form-group">
            <label class="xrequired" for="account">账号</label>
            <input type="text" class="form-control" id="account" name="account">
            <span style="display: none; color: red" id="userName_err">账号已存在</span>
            <span style="display: none; color: #1b6d85" id="userName_err2">账号不符合规范哦：账号由8~13位数字、字母或下划线组成</span>
            <br>
            <label class="xrequired" for="password">密码</label>
            <input type="password" class="form-control" id="password" name="password">
            <span style="display: none; color: #1b6d85" id="password_err">密码不符合规范哦：由8~13位数字、字母或下划线组成，字母至少一位</span>
        </div>
        <div class="form-group">
            <label for="password_1">确认密码</label>
            <input type="password" class="form-control" id="password_1">
            <span style="display: none; color: red" id="password_1_err">两次密码输入不一致</span>
        </div>
        <div class="form-group">
            <label for="email">QQ邮箱</label>
            <input type="text" class="form-control" id="email" name="email">
            <span style="display: none; color: #1b6d85" id="email_err">暂时只支持qq邮箱</span>
            <span style="display: none; color: red" id="email_err1">此邮箱已绑定账号，请更换</span>
        </div>
        <div class="form-group">
            <label for="emailE">验证码</label>
            <div class="input-group">
                <input type="text" class="form-control" id="emailE" name="emailE">
                <div class="input-group-btn" id="getEmail">
                    <div class="btn btn-default">获取验证码</div>
                </div>
            </div>
        </div>
        <% String err = (String) request.getAttribute("emailE_err");%>
        <p style="color: red"><%= err != null ? err : ""%></p>
        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block" disabled="disabled" id="register">注册</button>
        </div>
        <div class="form-group last">
            注册极客开发者头条即代表您同意<a href="#">极客开发者服务条款</a>
        </div>
    </form>
</div>
<jsp:include page="common/tail.jsp" flush="true"></jsp:include>
</body>
<script src="js/signup.js"></script>
</html>
