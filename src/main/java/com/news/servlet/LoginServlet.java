package com.news.servlet;

import com.news.Tool.Md5Util;
import com.news.dao.Impl.UserDaoImpl;
import com.news.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account = request.getParameter("account");
        String password = Md5Util.md5(request.getParameter("password"));
        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.getOne(account);
        if (user != null && password.equals(user.getPassword())) {
            if (user.getForBidden()) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect("index.jsp");
            } else {
                request.setAttribute("forbid", "账号已被封禁");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("err", "账号或密码输入错误");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
