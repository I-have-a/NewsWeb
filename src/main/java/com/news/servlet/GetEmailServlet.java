package com.news.servlet;

import com.news.Tool.MailDemoSum;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getEmailServlet")
public class GetEmailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        try {
            String authCode = MailDemoSum.textMail(email);
            request.getSession().setAttribute("authCode",authCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        this.doGet(request, response);
    }
}
