package com.news.model;

import java.util.Date;
import java.util.List;

public class Comment {
    private  Long id;//ID
    private String content;//内容
    private Date pubDate;//发布时间
    private String ipAddress;//公网IP
    private  News news;//新闻
    private  User creator;//评论人
    private  Integer likeNum;//点赞数量
    private  Integer disLikeNum;//点踩数量
    private Comment replyFor;//父评论
    private Boolean hidden;//是否可见
    private Long replyId;//被回复的id
    private List<Comment> replies;//回复的列表

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getDisLikeNum() {
        return disLikeNum;
    }

    public void setDisLikeNum(Integer disLikeNum) {
        this.disLikeNum = disLikeNum;
    }

    public Comment getReplyFor() {
        return replyFor;
    }

    public void setReplyFor(Comment replyFor) {
        this.replyFor = replyFor;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    public List<Comment> getReplies() {
        return replies;
    }

    public void setReplies(List<Comment> replies) {
        this.replies = replies;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", pubDate=" + pubDate +
                ", ipAddress='" + ipAddress + '\'' +
                ", news=" + news +
                ", creator=" + creator +
                ", likeNum=" + likeNum +
                ", disLikeNum=" + disLikeNum +
                ", replyFor=" + replyFor +
                ", hidden=" + hidden +
                ", replyId=" + replyId +
                ", replies=" + replies +
                '}';
    }
}
