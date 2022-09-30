package com.news.servlet;

import com.news.dao.Impl.UserDaoImpl;
import com.news.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/signupServlet")
public class SignupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        HashMap map = new HashMap();
        map.put("account",account);
        map.put("password",password);
        map.put("email",email);
        map.put("nickname","用户"+account);
        map.put("photo","1.jpg");
        map.put("birthDay",null);
        map.put("regDate",new Date());
        UserDaoImpl userDao = new UserDaoImpl();
        if (userDao.insert(map) != 0){
            response.getWriter().write("true");
        }else {
            response.getWriter().write("false");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
