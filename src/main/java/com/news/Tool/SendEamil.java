package com.news.Tool;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;
import java.util.Random;

public class SendEamil {

    /**
     *
     * @param Em:用户输入的qq邮箱
     * @return getAuthCode:获得的验证码
     * @throws GeneralSecurityException
     * @throws MessagingException
     */
    public String getAuthCode(String Em) throws GeneralSecurityException, MessagingException {
        Properties properties = new Properties();
        properties.setProperty("mail.host","smtp.qq.com");
        properties.setProperty("mail.transport.protocol","smtp");
        properties.setProperty("mail.smtp.auth","true");
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("2368688782@qq.com","mttxvsxaeolwdibd");
            }
        });
        session.setDebug(true);
        Transport transport = session.getTransport();
        transport.connect("smtp.qq.com","2368688782@qq.com","mttxvsxaeolwdibd");
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress("2368688782@qq.com"));
        mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(Em));
        String authCode = getRandomString(6);
        //邮件标题
        mimeMessage.setSubject("验证码");

        //邮件内容
        mimeMessage.setContent(authCode,"text/html;charset=UTF-8");
        //发送邮件
        transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
        transport.close();
        return authCode;
    }
    //length用户要求产生字符串的长度
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
