package com.news.servlet;

import com.news.dao.Impl.UserDaoImpl;
import com.news.model.User;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/selectUserServlet")
public class SelectUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        System.out.println("-----------" + userName + "----------");
        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.getOne(userName);
        if (user != null){
            response.getWriter().write("true");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
