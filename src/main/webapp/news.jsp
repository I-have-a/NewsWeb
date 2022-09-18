<%@ page import="com.news.dao.Impl.NewsDaoImpl" %>
<%@ page import="com.news.model.News" %><%--
  Created by IntelliJ IDEA.
  User: 23686
  Date: 2022/9/16
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新闻详情</title>
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
<div class="container">
    <div class="col-xs-8">
        <%
            NewsDaoImpl newsDao = new NewsDaoImpl();
            News news = newsDao.getOne(Integer.parseInt(request.getParameter("id")));
        %>
        <h1 class="news-title"><%= news.getTitle()%>
        </h1>
        <div class="news-status">25k阅读•<%= newsDao.pundateToString(news)%>
            <div class="label label-default">教育</div>
            <div class="label label-default">情感</div>
        </div>
        <div class="news-content">
            <%= news.getContent()%>
        </div>
    </div>
    <div class="col-xs-4">
        <div class="side-bar-card">
            <div class="card-title">相关推荐</div>
            <div class="card-body">
                <div class="list">
                    <div class="item clearfix">
                        <div class="col-xs-5 no-padding-h"><img src="https://dummyimage.com/1000x700/666/ccc" alt="">
                        </div>
                        <div class="col-xs-7">
                            <div class="title">记结婚高圆圆与赵又廷北京登</div>
                            <div class="desc">25k阅读•35分钟前发布</div>
                        </div>
                    </div>
                    <div class="item clearfix">
                        <div class="col-xs-5 no-padding-h"><img src="https://dummyimage.com/1000x700/666/ccc" alt="">
                        </div>
                        <div class="col-xs-7">
                            <div class="title">记结婚高圆圆与赵又廷北京登</div>
                            <div class="desc">25k阅读•35分钟前发布</div>
                        </div>
                    </div>
                    <div class="item clearfix">
                        <div class="col-xs-5 no-padding-h"><img src="https://dummyimage.com/1000x700/666/ccc" alt="">
                        </div>
                        <div class="col-xs-7">
                            <div class="title">记结婚高圆圆与赵又廷北京登</div>
                            <div class="desc">25k阅读•35分钟前发布</div>
                        </div>
                    </div>
                    <div class="item clearfix">
                        <div class="col-xs-5 no-padding-h"><img src="https://dummyimage.com/1000x700/666/ccc" alt="">
                        </div>
                        <div class="col-xs-7">
                            <div class="title">记结婚高圆圆与赵又廷北京登</div>
                            <div class="desc">25k阅读•35分钟前发布</div>
                        </div>
                    </div>
                    <div class="item clearfix">
                        <div class="col-xs-5 no-padding-h"><img src="https://dummyimage.com/1000x700/666/ccc" alt="">
                        </div>
                        <div class="col-xs-7">
                            <div class="title">记结婚高圆圆与赵又廷北京登</div>
                            <div class="desc">25k阅读•35分钟前发布</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="side-bar-card">
            <div class="card-title">24小时热闻</div>
            <div class="card-body">
                <div class="list">
                    <div class="item">
                        <a class="title" href="#">人工智能时代的到来，把全世界的科技巨头和汽车厂商推向了研发自动驾驶技术的浪潮中。</a>
                        <div class="desc">15k阅读　1k评论</div>
                    </div>
                    <div class="item">
                        <a class="title" href="#">人工智能时代的到来，把全世界的科技巨头和汽车厂商推向了研发自动驾驶技术的浪潮中。</a>
                        <div class="desc">15k阅读　1k评论</div>
                    </div>
                    <div class="item">
                        <a class="title" href="#">人工智能时代的到来，把全世界的科技巨头和汽车厂商推向了研发自动驾驶技术的浪潮中。</a>
                        <div class="desc">15k阅读　1k评论</div>
                    </div>
                    <div class="item">
                        <a class="title" href="#">人工智能时代的到来，把全世界的科技巨头和汽车厂商推向了研发自动驾驶技术的浪潮中。</a>
                        <div class="desc">15k阅读　1k评论</div>
                    </div>
                    <div class="item">
                        <a class="title" href="#">人工智能时代的到来，把全世界的科技巨头和汽车厂商推向了研发自动驾驶技术的浪潮中。</a>
                        <div class="desc">15k阅读　1k评论</div>
                    </div>
                    <div class="item">
                        <a class="title" href="#">人工智能时代的到来，把全世界的科技巨头和汽车厂商推向了研发自动驾驶技术的浪潮中。</a>
                        <div class="desc">15k阅读　1k评论</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="footer">
    Copyright © 2017 jkdev.cn | 极客开发者2017版
</div>
</body>
</html>
