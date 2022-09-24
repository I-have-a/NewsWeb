<%@ page import="com.news.dao.Impl.CategoryDaoImpl" %>
<%@ page import="com.news.model.Category" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 23686
  Date: 2022/9/21
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                    String categoryNum = request.getParameter("category");
                    CategoryDaoImpl categoryDao = new CategoryDaoImpl();
                    List<Category> categories = categoryDao.getAll();
                %>
                <li <% if (categoryNum == null){%>class="active"<% }%>><a href="index.jsp">首页</a></li>
                <% for (Category category : categories) {%>
                <li <% if (categoryNum != null && Integer.parseInt(categoryNum) == category.getId()){%>class="active"<% }%>><a href="index.jsp?category=<%= category.getId()%>"><%=category.getName()%></a></li>
                <%}%>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <%
                    String nickname = (String) session.getAttribute("nickname");
                    if (nickname != null){
                %>
                        <li><a><%= nickname%></a></li>
                        <li><a href="logout.jsp">退出</a></li>
                    <%}else{%>
                        <li><a href="login.jsp">登陆</a></li>
                    <%}%>
                <li><a href="signup.jsp">注册</a></li>
            </ul>
        </div>
    </div>
</div>