package com.news.web.servlet;

import com.news.dao.Impl.UserDaoImpl;
import com.news.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/userinfoServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10,maxFileSize = 1024 * 1024 * 50,maxRequestSize = 1024 * 1024 * 100)
public class UserinfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "img/photos";

    /**
     * 上传数据及保存文件
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String realPath = request.getServletContext().getRealPath("");
        String uploadFilePath = realPath + File.separator + UPLOAD_DIRECTORY;
        HttpSession session = request.getSession();
        User userT = (User) session.getAttribute("user");
        for (Part part : request.getParts()) {
            String submittedFileName = part.getSubmittedFileName();
            if (submittedFileName != null){
                part.write(uploadFilePath + File.separator + submittedFileName);
                userT.setPhoto(submittedFileName);
            }
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            userT.setBirthDay(df.parse(request.getParameter("birthday")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        userT.setNickname(request.getParameter("nickname"));
        userT.setEmail(request.getParameter("email"));
        userT.setMobile(request.getParameter("mobile"));
        userT.setAccount(request.getParameter("account"));
        UserDaoImpl userDao = new UserDaoImpl();
        if (userDao.update(userT)){
            session.setAttribute("user",userT);
            response.sendRedirect("index.jsp");
            session.setAttribute("updateMark","修改成功");
        }else {
            response.sendRedirect("userinfo.jsp");
            session.setAttribute("updateMark","修改失败");
        }
    }
}