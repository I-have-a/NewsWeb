<%@ page import="com.news.model.User" %>
<%@ page import="com.news.dao.Impl.UserDaoImpl" %><%--
  Created by IntelliJ IDEA.
  User: 23686
  Date: 2022/9/23
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String account = request.getParameter("account");
    String password = request.getParameter("password");
    UserDaoImpl userDao = new UserDaoImpl();
    User user = userDao.getOne(account);
    if (user != null && password.equals(user.getPassword())){
        session.setAttribute("account",user.getAccount());
        session.setAttribute("photo",user.getPhoto());
        session.setAttribute("password",user.getPassword());
        session.setAttribute("nickname",user.getNickname());
        response.sendRedirect("index.jsp");
    }else {
        request.setAttribute("err","账号或密码输入错误");
        request.getRequestDispatcher("login.jsp").forward(request,response);
    }
%>
