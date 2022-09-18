<%@ page import="com.news.model.Category" %>
<%@ page import="com.news.dao.Impl.CategoryDaoImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="com.news.model.Tag" %>
<%@ page import="com.news.dao.Impl.TagDaoImpl" %>
<%@ page import="com.news.model.News" %>
<%@ page import="com.news.dao.Impl.NewsDaoImpl" %>
<%--
  Created by IntelliJ IDEA.
  User: 23686
  Date: 2022/9/9
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>极客开发者</title>
    <link rel="stylesheet" href="./css/bootstrap.min.css">
    <link rel="stylesheet" href="./css/main.css">
</head>
<body>
<div class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <a href="index.jsp" class="navbar-brand"></a>
        </div>
        <!-- class="visible-xs-inline-block"：在超小屏幕上显示-->
        <label for="toggle-checkbox" id="toggle-label" class="visible-xs-inline-block">菜单</label>
        <input type="checkbox" class="hidden" id="toggle-checkbox">
        <div class="hidden-xs">
            <ul class="nav navbar-nav">
                <%
                    CategoryDaoImpl categoryDao = new CategoryDaoImpl();
                    List<Category> categories = categoryDao.getAll();
                %>
                <li class="active"><a href="index.jsp">首页</a></li>
                <% for (Category category : categories) {%>
                <li><a href="index.jsp?category=<%= category.getId()%>"><%=category.getName()%>
                </a></li>
                <%}%>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="login.jsp">登陆</a></li>
                <li><a href="signup.jsp">注册</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-sm-2">
            <div class="list-group side-bar hidden-xs">
                <%
                    TagDaoImpl tagDao = new TagDaoImpl();
                    List<Tag> tags = tagDao.getAll();
                    for (Tag tag : tags) {%>
                <a href="index.jsp?tag=<%= tag.getId()%>" class="list-group-item"><%=tag.getName()%>
                </a>
                <%}%>
            </div>
        </div>
        <div class="col-sm-7">
            <div class="news-list">
                <%
                    List<News> newsList;
                    NewsDaoImpl newsDao = new NewsDaoImpl();
                    String tag = request.getParameter("tag");
                    newsList = newsDao.getAll();
                    if (tag != null) {
                        newsList = newsDao.getTagNews(Integer.parseInt(tag));
                    }
                    String category = request.getParameter("category");
                    if (category != null) {
                        newsList = newsDao.getWhere(Integer.parseInt(category));
                    }
                    for (News news : newsList) {
                %>
                <div class="news-list-item clearfix">
                    <div class="col-xs-5">
                        <img src="img/<%= news.getImg()%>" alt="">
                    </div>
                    <div class="col-xs-7">
                        <a href="news.jsp?id=<%= news.getId()%>" class="title"><%= news.getTitle()%>
                        </a>
                        <div class="info">
                            <span class="avatar"><img src="img/logo.png" alt=""></span>
                            <span>王花花</span>•
                            <span>25k评论</span>•
                            <span><%= newsDao.pundateToString(news)%></span>
                        </div>
                    </div>
                </div>
                <%}%>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="search-bar">
                <label>
                    <input type="search" class="form-control" placeholder="搜一下">
                </label>
            </div>
            <div class="side-bar-card flag clearfix">
                <div class="col-xs-5">
                    <img src="img/1-1.png" alt="">
                </div>
                <div class="col-xs-7">
                    <div class="text-lg">有害信息举报专区</div>
                    <div>举报电话：12377</div>
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
</div>
<div class="footer">
    Copyright © 2017 jkdev.cn | 极客开发者2017版
</div>
</body>
</html>
