package com.news.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
    private int id;//ID
    private String account; //账号
    private String nickname;//昵称
    private String password;//密码
    private String photo;//头像路径
    private String birthDay;//生日
    private String email;//邮箱
    private String mobile;//电话号码
    private Date regDate;//注册时间
    private boolean forBidden;//是否封禁

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", photo='" + photo + '\'' +
                ", birthDay=" + birthDay +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", regDate=" + regDate +
                ", forBidden='" + forBidden + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getBirthDay() {
        Date parse = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            parse = simpleDateFormat.parse(birthDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

    public void setBirthDay(Date birthDay) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String format = String.valueOf(birthDay);
        if (birthDay != null){
            format = df.format(birthDay);
        }
        this.birthDay = format;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public boolean getForBidden() {
        return forBidden;
    }

    public void setForBidden(int forBidden) {
        this.forBidden = forBidden == 0;
    }
}