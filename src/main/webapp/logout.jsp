<%--
  Created by IntelliJ IDEA.
  User: 23686
  Date: 2022/9/23
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    session.invalidate();
    response.sendRedirect("index.jsp");
%>