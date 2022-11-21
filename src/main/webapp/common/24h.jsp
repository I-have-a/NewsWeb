<%@ page import="com.news.model.News" %>
<%@ page import="java.util.List" %>
<%@ page import="com.news.Impl.NewsDaoImpl" %><%--
  Created by IntelliJ IDEA.
  User: 23686
  Date: 2022/9/23
  Time: 9:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<div class="side-bar-card">
    <div class="card-title">24小时热闻</div>
    <div class="card-body">
        <div class="list">
            <%
                NewsDaoImpl newsDao = new NewsDaoImpl();
                List<News> newsList1 = newsDao.RecommendNews();
                String contextPath = request.getContextPath();
                for (News news1 : newsList1) {%>
            <div class="item">
                <a class="title" href="<%= contextPath%>/news.jsp?id=<%= news1.getId()%>"><%= news1.getTitle()%>
                </a>
                <div class="desc">15k阅读　1k评论</div>
            </div>
            <%}%>
        </div>
    </div>
</div>
