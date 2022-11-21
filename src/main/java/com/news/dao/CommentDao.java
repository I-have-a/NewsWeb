package com.news.dao;

import com.news.model.Comment;

import java.util.List;
import java.util.Map;

public interface CommentDao {

    int insert(Map map);

    List<Comment> getCommentsByNewsId(int newsId);

    /**
     * 更新点赞数
     *
     * @param commentId 评论ID
     * @param like      true:点赞 或 false:取消点赞
     * @return 点赞数量
     */
    int updateLikeNum(int commentId, boolean like);

    /**
     * 更新点踩数
     *
     * @param commentId 评论ID
     * @param dislike   true:点踩 或 false:取消点踩
     * @return 点踩数量
     */
    int updateDislikeNum(int commentId, boolean dislike);
}
