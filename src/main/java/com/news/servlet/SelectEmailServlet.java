package com.news.servlet;

import com.news.dao.Impl.UserDaoImpl;
import com.news.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
    邮箱验证
 */
@WebServlet("/selectEmailServlet")
public class SelectEmailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.getSingleOne("email",email);
        if (user != null) {
            response.getWriter().write("true");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}
