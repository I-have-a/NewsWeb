package com.news.web.servlet;

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
import java.util.Date;

@WebServlet("/signupServlet")
public class SignupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("authCode").equals(request.getParameter("emailE")) &&session.getAttribute("authCode") != null){
            String account = request.getParameter("account");
            String email = request.getParameter("email");
            String password = Md5Util.md5(request.getParameter("password"));
            User user = new User();
            user.setAccount(account);
            user.setPassword(password);
            user.setEmail(email);
            user.setPhoto("1.jpg");
            user.setNickname("用户"+account);
            user.setRegDate(new Date());
            user.setBirthDay(null);
            UserDaoImpl userDao = new UserDaoImpl();
            if (userDao.insert(user) != 0){
                session.setAttribute("user", user);
                response.sendRedirect("index.jsp");
            }else {
                response.sendRedirect("signup.jsp");
            }
        }else {
            request.setAttribute("emailE_err","验证码错误,请再次获取");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
