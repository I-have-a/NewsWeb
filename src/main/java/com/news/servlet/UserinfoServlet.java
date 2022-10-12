package com.news.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.File;
import java.io.IOException;

@WebServlet("/userinfoServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10,maxFileSize = 1024 * 1024 * 50,maxRequestSize = 1024 * 1024 * 100)
public class UserinfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "upload";

    /**
     * 上传数据及保存文件
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String realPath = request.getServletContext().getRealPath("");
        String uploadFilePath = realPath + File.separator + UPLOAD_DIRECTORY;
        File file = new File(uploadFilePath);
        if (!file.exists()) file.mkdirs();
        for (Part part : request.getParts()) {
            String submittedFileName = part.getSubmittedFileName();
            part.write(uploadFilePath + File.separator + submittedFileName);
        }
//        request.setAttribute("user", );
        getServletContext().getRequestDispatcher("/").forward(request,response);
    }
}
