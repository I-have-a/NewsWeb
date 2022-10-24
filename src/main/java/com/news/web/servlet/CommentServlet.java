package com.news.web.servlet;

import com.news.Impl.CommentDaoImpl;
import com.news.model.Comment;
import com.news.model.User;
import com.news.web.servlet.BaseServlet;
import javax.servlet.annotation.WebServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/Comment/*")
public class CommentServlet extends BaseServlet {
    public void putComment(javax.servlet.http.HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        User user = (User) request.getSession().getAttribute("user");
        Map map = new HashMap();
        map.put("content",request.getParameter("content"));
        map.put("hidden",false);
        map.put("newsId",Integer.parseInt(request.getParameter("newsId")));
        map.put("creator",user.getId());
        map.put("ipaddress",request.getRemoteAddr());
        map.put("likeNum",0);
        map.put("dislikeNum",0);
        map.put("pubDate",new Date());
        map.put("replyForId",request.getParameter("replyForId"));
        CommentDaoImpl commentDao = new CommentDaoImpl();
        commentDao.insert(map);
        response.sendRedirect(request.getContextPath() + "/news.jsp?id=" + request.getParameter("newsId"));
    }
}
