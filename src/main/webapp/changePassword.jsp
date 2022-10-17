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
<div id="app">
    <div class="container">
        <div class="container">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        修改密码
                    </h3>
                </div>
                <div class="panel-body" style="padding: 20px;">
                    <form action="User/ChangePassword" class="form-horizontal" role="form" method="post"
                          enctype="multipart/form-data">
                        <div class="form-group">
                            <label for="account" class="col-sm-2 control-label">账户：</label>
                            <div class="col-sm-10">
                                <input type="text" readonly class="form-control" name="account" id="account" value="${user.account}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="oldPassword" class="col-sm-2 control-label">原密码：</label>
                            <div class="col-sm-10">
                                <input v-model="oldPassword" type="text" class="form-control" name="oldPassword" id="oldPassword" @change="old()" placeholder="请输入原密码">
                                <p class="alert alert-danger" v-if="!flogOld">密码不正确，请重新输入</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="newPassword" class="col-sm-2 control-label">新密码：</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="newPassword" id="newPassword"
                                       placeholder="请输入您的新密码" v-model="newPassword" @change="newP()">
                                <p class="alert alert-danger" v-if="!flogNew">密码不符合规范哦：由8~13位数字、字母或下划线组成，字母至少一位</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="enterPassword" class="col-sm-2 control-label">确认密码：</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="enterPassword" id="enterPassword"
                                       placeholder="请确认您密码" v-model="enterPassword" @change="newEnter()">
                                <p class="alert alert-danger" v-if="!flogEnter">两次密码不一致，请重新输入</p>
                            </div>
                        </div>
                        <div class="form-group" v-model="AllCont">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-primary btn-block" :disabled="flogAll">提交</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="common/tail.jsp" flush="true"></jsp:include>
<script src="js/vue.js"></script>
<script src="js/lmd5.js"></script>
<script>
    new Vue({
        el:"#app",
        data(){
            return{
                oldPassword:"",
                newPassword:"",
                enterPassword:"",
                flogOld:true,
                flogEnter:true,
                flogNew:true,
                flogAll:true
            }
        },
        methods:{
            //总体判定和注册类似
            old(){
                this.flogOld = false;
                this.flogOld = hex_md5(this.oldPassword) === "${user.password}";
                this.AllCont();
                return this.flogOld;
            },
            newEnter(){
                this.flogEnter = this.enterPassword === this.newPassword;
                this.AllCont();
                return this.flogEnter
            },
            newP(){
                this.flogNew = /^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9 _]{8,13}$/.test(this.newPassword);
                this.newEnter();
                this.AllCont();
                return this.flogNew
            },
            AllCont(){
                this.flogAll = !(this.flogOld && this.flogEnter && this.flogNew && this.newPassword !== "");
            }
        }
    });
</script>
</body>
</html>