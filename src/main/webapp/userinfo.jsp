<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.news.model.User" %><%--
  Created by IntelliJ IDEA.
  User: zhao
  Date: 2022/10/12
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人信息</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<jsp:include page="common/header.jsp" flush="true"></jsp:include>
<div class="container">
    <div class="container">
        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title">
                    修改个人信息
                </h3>
            </div>
            <div class="panel-body" style="padding: 20px;">
                <form action="userinfoServlet" class="form-horizontal" role="form" method="post"
                      enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="account" class="col-sm-2 control-label">账户：</label>
                        <div class="col-sm-10">
                            <input type="text" readonly class="form-control" name="account" id="account"
                                   value="${user.account}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="nickname" class="col-sm-2 control-label">昵称：</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="nickname" id="nickname" placeholder="请输入昵称"
                                   value="${user.nickname}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="birthday" class="col-sm-2 control-label">生日：</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="birthday" id="birthday"
                                   placeholder="请选择您的出生日期" value="<c:if test="${user.birthDay!=null}">${user.birthDay}</c:if>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-sm-2 control-label">电子邮件：</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="email" id="email" placeholder="请输入电子邮件"
                                   value="${user.email}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="mobile" class="col-sm-2 control-label">手机号码：</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="mobile" id="mobile" placeholder="请输入手机号码"
                                   value="<c:if test="${user.mobile!=null}">${user.mobile}</c:if>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="photo" class="col-sm-2 control-label"><img src="img/photos/${user.photo}" class="img-thumbnail" width="70px" height="70px"><br> 头像：</label>
                        <div class="col-sm-10">
                            <input type="file" class="form-control" name="photo" id="photo" placeholder="请选择头像照片"
                                   accept="image/jpg,image/jpeg,image/png" value="${user.photo}">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">确定</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="common/tail.jsp" flush="true"></jsp:include>
<script src="./js/jquery.min.js"></script>
<script src="./js/bootstrap-datepicker.min.js"></script>
<script src="./js/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="./js/jquery.validate.min.js"></script>
<script src="./js/messages_zh.js"></script>
<script>
    $().ready(function (){
        // 设置日期选择器
        $('#birthday').datepicker({
            language: 'zh-CN', //语言
            clearBtn: true,//清除按钮
            format: "yyyy-mm-dd"//日期格式
        });
    });
</script>
</body>
</html>
