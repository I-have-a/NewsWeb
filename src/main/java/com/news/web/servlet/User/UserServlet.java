package com.news.web.servlet.User;

import com.news.Tool.MailDemoSum;
import com.news.Tool.Md5Util;
import com.news.dao.Impl.UserDaoImpl;
import com.news.model.User;
import com.news.web.servlet.BaseServlet;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//这回修改了一下文件结构，用反射的方式

@WebServlet("/User/*")
@MultipartConfig(fileSizeThreshold = 10*1024*1024,maxFileSize = 50*1024*1024,maxRequestSize = 100*1024*1024)
public class UserServlet extends BaseServlet {

    private static final long serialVersionUID = 1L;

    // 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "img/photos";

    /* 检查邮箱重复 */
    public void SelectEmail(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.getSingleOne("email", email);
        if (user != null) {
            HttpSession session = request.getSession();
            User user1 = (User) session.getAttribute("user");
            if (user.getId() != user1.getId()){
                response.getWriter().write("true");
            }
        }
    }

    /* 检查用户账号重复 */
    public void SelectUser(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.getSingleOne("account", userName);
        if (user != null) {
            response.getWriter().write("true");
        }
    }

    /* 获得验证码邮件 */
    public void GetEmail(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {
        String email = request.getParameter("email");
        try {
            String authCode = MailDemoSum.textMail(email);
            request.getSession().setAttribute("authCode", authCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* 修改信息 */
    public void Userinfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String realPath = request.getServletContext().getRealPath("");
        String uploadFilePath = realPath + File.separator + UPLOAD_DIRECTORY;
        HttpSession session = request.getSession();
        User userT = (User) session.getAttribute("user");
        //这边我用的servlet3文件上传
        for (Part part : request.getParts()) {
            String submittedFileName = part.getSubmittedFileName();
            if (submittedFileName != null && !submittedFileName.equals("")) {
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
        String authCode = (String) session.getAttribute("authCode");
        if(authCode != null){
            String emailE = (String) session.getAttribute("emailE");
            if (!authCode.equals(emailE)){
                session.setAttribute("authCodeErr","验证码错误，请重试");
                response.sendRedirect(request.getContextPath() + "/userinfo.jsp");
            }
        }
        userT.setEmail(request.getParameter("email"));
        userT.setMobile(request.getParameter("mobile"));
        userT.setAccount(request.getParameter("account"));
        UserDaoImpl userDao = new UserDaoImpl();
        if (userDao.update(userT)) {
            session.setAttribute("user", userT);
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/userinfo.jsp");
        }
    }

    /* 注册 */
    public void Signup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("authCode") != null && session.getAttribute("authCode").equals(request.getParameter("emailE"))) {
            String account = request.getParameter("account");
            String email = request.getParameter("email");
            String password = Md5Util.md5(request.getParameter("password"));
            User user = new User();
            user.setAccount(account);
            user.setPassword(password);
            user.setEmail(email);
            user.setPhoto("1.jpg");
            user.setNickname("用户" + account);
            user.setRegDate(new Date());
            user.setBirthDay(null);
            UserDaoImpl userDao = new UserDaoImpl();
            if (userDao.insert(user) != 0) {
                session.setAttribute("user", user);
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/signup.jsp");
            }
        } else {
            request.setAttribute("emailE_err", "验证码错误,请再次获取");
            request.getRequestDispatcher(request.getContextPath() + "/signup.jsp").forward(request, response);
        }
    }

    /* 登陆 */
    public void Login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account = request.getParameter("account");
        String password = Md5Util.md5(request.getParameter("password"));
        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.getSingleOne("account", account);
        if (user != null && password.equals(user.getPassword())) {
            if (user.getForBidden()) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            } else {
                request.setAttribute("forbid", "账号已被封禁");
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        } else {
            request.setAttribute("err", "账号或密码输入错误");
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }

    /* 修改密码 */
    public void ChangePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String newPassword = request.getParameter("newPassword");
        user.setPassword(Md5Util.md5(newPassword));
        UserDaoImpl userDao = new UserDaoImpl();
        if (userDao.update(user)) response.sendRedirect(request.getContextPath() + "/index.jsp");
        else response.sendRedirect(request.getContextPath() + "/changePassword.jsp");
    }
}