package com.news.Tool;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;
import java.util.Random;

/**
 * 作者：沈公子
 * 日期：2022/7/15 - 18:04
 * 作用：发送邮件
 * 需求：文本，图片，附件(文本，图片，视频，音乐)与拼接发送
 * setContent();    文本
 * setContentID();  图片
 * setFileName();   附件
 * 使用的路径是本项目的 resources 目录
 */
public class MailDemoSum {

    public static void main(String[] args){

        // 发送邮件（文本）
        //   textMail();

        // 发送邮件（图片）
        //   imageMail();

        // 发送邮件（附件：文本、图片、视频、音乐等）
        //  annexMail();

        // 发送邮件（文本，附件，图片，拼接）
        //splitMail();

    }


    /**
     * 文本，附件，图片，拼接邮件
     */
    private static void splitMail() throws Exception {

        // 给用户发送邮件的邮箱
        final String from = "发件人QQ邮箱";
        // 邮箱的用户名
        final String username = "发件人QQ邮箱或邮箱用户名";
        // 邮箱授权码
        final String password = "授权码";
        // 发送邮件的服务器地址，QQ服务器
        final String host = "smtp.qq.com";
        // 接收人邮箱
        final String to = "收件人QQ邮箱";
        // 邮件主题
        final String title = "邮件主题";

        // 使用QQ邮箱时配置
        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.qq.com");    // 设置QQ邮件服务器
        prop.setProperty("mail.transport.protocol", "smtp");      // 邮件发送协议
        prop.setProperty("mail.smtp.auth", "true");      // 需要验证用户名和密码

        // 关于QQ邮箱，还要设置SSL加密，其他邮箱不需要
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);

        // 创建定义整个邮件程序所需的环境信息的 Session 对象，QQ才有，其他邮箱就不用了
        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 发件人邮箱用户名，授权码
                return new PasswordAuthentication(username, password);
            }
        });

        // 开启 Session 的 debug 模式，这样就可以查看程序发送 Email 的运行状态
        session.setDebug(true);

        // 通过 session 得到 transport 对象
        Transport ts = session.getTransport();

        // 使用邮箱的用户名和授权码连上邮箱服务器
        ts.connect(host, username, password);

        // 创建邮件，写邮件
        // 需要传递 session
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from)); // 指明邮件的发件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));   // 指明邮件的收件人
        message.setSubject(title);     // 邮件主题

        // 图片
        MimeBodyPart body1 = new MimeBodyPart();
        body1.setDataHandler(new DataHandler(new FileDataSource("D:\\Java\\JavaWeb\\daily_demo\\day40\\src\\main\\resources\\1.png")));
        body1.setContentID("1.png");

        // 文本
        MimeBodyPart body2 = new MimeBodyPart();
        body2.setContent("我不是广告，<img src='cid:1.png'>", "text/html;charset=utf-8");

        // 附件
        MimeBodyPart body3 = new MimeBodyPart();
        body3.setDataHandler(new DataHandler(new FileDataSource("D:\\Java\\JavaWeb\\daily_demo\\day40\\src\\main\\resources\\sp.mp4")));
        body3.setFileName("sp.mp4");

        // 附件
        MimeBodyPart body4 = new MimeBodyPart();
        body4.setDataHandler(new DataHandler(new FileDataSource("D:\\Java\\JavaWeb\\daily_demo\\day40\\src\\main\\resources\\a.txt")));
        body4.setFileName("2.png");

        // 拼接邮件正文内容
        MimeMultipart mimeMultipart1 = new MimeMultipart();
        mimeMultipart1.addBodyPart(body1);
        mimeMultipart1.addBodyPart(body2);
        mimeMultipart1.setSubType("related");   // 文本和图片内嵌成功

        // 将拼装好的正文内容设置为主体
        MimeBodyPart contentText = new MimeBodyPart();
        contentText.setContent(mimeMultipart1);

        // 拼接附件与正文内容
        MimeMultipart allFile = new MimeMultipart();
        allFile.addBodyPart(body3);
        allFile.addBodyPart(body4);
        allFile.addBodyPart(contentText);
        allFile.setSubType("mixed");    // 正文和附件都存在邮件中，所以类型设置为 mixed，三种方式，两个附件单词都可用：mixed(附件) > related(附件) > alternative(纯文本)

        // 设置到消息中，保存修改
        message.setContent(allFile);  // 把最后编辑好的邮件放到消息当中
        message.saveChanges();   // 保存修改

        // 发送邮件
        ts.sendMessage(message, message.getAllRecipients());

        // 释放资源
        ts.close();
    }

    /**
     * 附件邮件发送（附件：文本、图片、视频、音乐等）
     */
    private static void annexMail() throws Exception {

        // 给用户发送邮件的邮箱
        final String from = "发件人QQ邮箱";
        // 邮箱的用户名
        final String username = "发件人QQ邮箱或邮箱用户名";
        // 邮箱授权码
        final String password = "授权码";
        // 发送邮件的服务器地址，QQ服务器
        final String host = "smtp.qq.com";
        // 接收人邮箱
        final String to = "收件人QQ邮箱";
        // 邮件主题
        final String title = "邮件主题";

        // 使用QQ邮箱时配置
        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.qq.com");    // 设置QQ邮件服务器
        prop.setProperty("mail.transport.protocol", "smtp");      // 邮件发送协议
        prop.setProperty("mail.smtp.auth", "true");      // 需要验证用户名和密码

        // 关于QQ邮箱，还要设置SSL加密，其他邮箱不需要
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);

        // 创建定义整个邮件程序所需的环境信息的 Session 对象，QQ才有，其他邮箱就不用了
        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 发件人邮箱用户名，授权码
                return new PasswordAuthentication(username, password);
            }
        });

        // 开启 Session 的 debug 模式，这样就可以查看程序发送 Email 的运行状态
        session.setDebug(true);

        // 通过 session 得到 transport 对象
        Transport ts = session.getTransport();

        // 使用邮箱的用户名和授权码连上邮箱服务器
        ts.connect(host, username, password);

        // 创建邮件，写邮件
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from)); // 指明邮件的发件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));   // 指明邮件的收件人
        message.setSubject(title);     // 邮件主题
        message.setDataHandler(new DataHandler(new FileDataSource("D:\\Java\\JavaWeb\\daily_demo\\day40\\src\\main\\resources\\aa.txt")));
        message.setFileName("sp.mp4");

        // 发送邮件
        ts.sendMessage(message, message.getAllRecipients());

        // 释放资源
        ts.close();
    }

    /**
     * 图片邮件发送
     */
    private static void imageMail() throws Exception {

        // 给用户发送邮件的邮箱
        final String from = "发件人QQ邮箱";
        // 邮箱的用户名
        final String username = "发件人QQ邮箱或邮箱用户名";
        // 邮箱授权码
        final String password = "授权码";
        // 发送邮件的服务器地址，QQ服务器
        final String host = "smtp.qq.com";
        // 接收人邮箱
        final String to = "收件人QQ邮箱";
        // 邮件主题
        final String title = "邮件主题";

        // 使用QQ邮箱时配置
        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.qq.com");    // 设置QQ邮件服务器
        prop.setProperty("mail.transport.protocol", "smtp");      // 邮件发送协议
        prop.setProperty("mail.smtp.auth", "true");      // 需要验证用户名和密码

        // 关于QQ邮箱，还要设置SSL加密，其他邮箱不需要
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);

        // 创建定义整个邮件程序所需的环境信息的 Session 对象，QQ才有，其他邮箱就不用了
        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 发件人邮箱用户名，授权码
                return new PasswordAuthentication(username, password);
            }
        });

        // 开启 Session 的 debug 模式，这样就可以查看程序发送 Email 的运行状态
        session.setDebug(true);

        // 通过 session 得到 transport 对象
        Transport ts = session.getTransport();

        // 使用邮箱的用户名和授权码连上邮箱服务器
        ts.connect(host, username, password);

        // 创建邮件，写邮件
        // 需要传递 session
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from)); // 指明邮件的发件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));   // 指明邮件的收件人
        message.setSubject(title);     // 邮件主题

        // 准备图片数据
        MimeBodyPart image = new MimeBodyPart();
        // 图片需要经过数据处理，DataHandler：数据处理
        DataHandler dh = new DataHandler(new FileDataSource("D:\\Java\\JavaWeb\\daily_demo\\day40\\src\\main\\resources\\1.png"));
        image.setDataHandler(dh);   // 在我们的 Body 中放入这个处理的图片数据
        image.setContentID("imageID");    // 给图片设置一个ID，后面需要，设置 cid

        // 准备正文数据
        MimeBodyPart text = new MimeBodyPart();
        text.setContent("图片邮件<img src='cid:imageID'>", "text/html;charset=UTF-8"); // 邮件内容

        // 拼接文本与图片
        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(text);
        mm.addBodyPart(image);
        mm.setSubType("related");    // 三种方式，两个附件单词都可用：mixed(附件) > related(附件) > alternative(纯文本)

        // 设置到消息中，保存修改
        message.setContent(mm);  // 把最后编辑好的邮件放到消息当中
        message.saveChanges();   // 保存修改

        // 发送邮件
        ts.sendMessage(message, message.getAllRecipients());

        // 释放资源
        ts.close();
    }

    /**
     * 文本邮件发送
     *
     * @param Em 用户邮箱
     * @return 浏览器校验码
     * @throws Exception 管他什么异常，抛就对了
     */
    public static String textMail(String Em) throws Exception {
        // 给用户发送邮件的邮箱
        final String from = "2368688782@qq.com";
        // 邮箱的用户名
        final String username = "2368688782@qq.com";
        // 邮箱授权码
        final String password = "wmxjdxxqwawsdieh";
        // 发送邮件的服务器地址，QQ服务器
        final String host = "smtp.qq.com";
        // 接收人邮箱
        final String to = Em;
        // 邮件主题
        final String title = "验证码";

        // 使用QQ邮箱时配置
        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.qq.com");    // 设置QQ邮件服务器
        prop.setProperty("mail.transport.protocol", "smtp");      // 邮件发送协议
        prop.setProperty("mail.smtp.auth", "true");      // 需要验证用户名和密码

        // 关于QQ邮箱，还要设置SSL加密，其他邮箱不需要
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);

        // 创建定义整个邮件程序所需的环境信息的 Session 对象，QQ才有，其他邮箱就不用了
        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 发件人邮箱用户名，授权码
                return new PasswordAuthentication(username, password);
            }
        });

        // 开启 Session 的 debug 模式，这样就可以查看程序发送 Email 的运行状态
        session.setDebug(true);

        // 通过 session 得到 transport 对象
        Transport ts = session.getTransport();

        // 使用邮箱的用户名和授权码连上邮箱服务器
        ts.connect(host, username, password);
        String randomString = getRandomString(6);
        // 创建邮件，写邮件
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from)); // 指明邮件的发件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));   // 指明邮件的收件人
        message.setSubject(title);     // 邮件主题
        message.setContent(randomString, "text/html;charset=utf-8");    // 邮件内容

        // 发送邮件
        ts.sendMessage(message, message.getAllRecipients());

        // 释放资源
        ts.close();
        return randomString;
    }

    /**
     * 生成 length 长的随机字符串
     *
     * @param length 字符串长度
     * @return sb 随机字符串
     */
    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}