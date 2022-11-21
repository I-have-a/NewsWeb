<%--
  Created by IntelliJ IDEA.
  User: 23686
  Date: 2022/9/17
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登陆</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<jsp:include page="common/header.jsp" flush="true"></jsp:include>
<div class="container container-small">
    <h1>登录
        <small>没有账号？<a href="signup.jsp">注册</a></small>
    </h1>
    <form action="User/Login" method="post">
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
