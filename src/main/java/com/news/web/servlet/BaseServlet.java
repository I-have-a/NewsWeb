package com.news.web.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        String uri = req.getRequestURI();
        int i = uri.lastIndexOf('/');
        int end = uri.indexOf('?');
        String methodName;
        methodName = end == -1 ? uri.substring(i + 1) : uri.substring(i + 1, end);
        Class<? extends BaseServlet> ac = this.getClass();
        try {
            Method method = ac.getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
