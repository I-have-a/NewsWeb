<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.news.dao.Impl.CategoryDaoImpl" %>
<%@ page import="com.news.model.Category" %>
<%@ page import="com.news.model.User" %><%--
  Created by IntelliJ IDEA.
  User: 41150
  Date: 2022-09-21
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <a href="./index.jsp" class="navbar-brand"></a>
        </div>
        <!-- class="visible-xs-inline-block"：在超小屏幕上显示-->
        <label for="toggle-checkbox" id="toggle-label" class="visible-xs-inline-block">菜单</label>
        <input type="checkbox" class="hidden" id="toggle-checkbox">
        <div class="hidden-xs">
            <ul class="nav navbar-nav">
                <%
                    String category = request.getParameter("category"); // 获取新闻分类参数

                    // 调用Dao，获取新闻分类列表
                    CategoryDaoImpl categoryDao = new CategoryDaoImpl();
                    List<Category> categoryList = categoryDao.getAll();
                %>
                <li <%if (category == null) {%> class="active"<%}%>><a href="./index.jsp">首页</a></li>
                <% for (Category c : categoryList) {%>
                <li <%if(category!=null && c.getId() == Integer.parseInt(category)){%>class="active"<%}%>><a
                        href="./index.jsp?category=<%=c.getId()%>"><%=c.getName()%>
                </a></li>
                <%}%>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${user== null || user == ''}">
                    <li><a href="./login.jsp">登录</a></li>
                    <li><a href="./signup.jsp">注册</a></li>
                </c:if>
                <c:if test="${user != null}">
                    <li><a href="#"><img class="img-circle" width="40px" src="img/photos/${user.photo}" alt=""></a>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">${user.nickname}<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="./userinfo.jsp">个人信息</a></li>
                            <li><a href="#">修改密码</a></li>
                            <li class="divider"></li>
                            <li><a href="./logout.jsp">退出</a></li>
                        </ul>
                    </li>
                    <li><a href="./signup.jsp">注册</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</div>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/jquery-3.2.1.min.js"></script>