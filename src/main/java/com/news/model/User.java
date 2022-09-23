package com.news.model;

import java.util.Date;

public class User {
    private int id;//ID
    private String account; //账号
    private String nickname;//昵称
    private String password;//密码
    private String photo;//头像路径
    private Date birthDay;//生日
    private String email;//邮箱
    private String mobile;//电话号码
    private Date regDate;//注册时间
    private String forBidden;//

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
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
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

    public String getForBidden() {
        return forBidden;
    }

    public void setForBidden(String forBidden) {
        this.forBidden = forBidden;
    }
}