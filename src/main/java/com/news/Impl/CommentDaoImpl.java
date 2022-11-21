package com.news.Impl;

import com.news.Tool.SqlLink;
import com.news.dao.CommentDao;
import com.news.model.Comment;
import mybatis.CommentMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommentDaoImpl implements CommentDao {

    @Override
    public int insert(Map map) {
        SqlSession session = SqlLink.getSqlSessionFactory().openSession();
        CommentMapper commentMapper = session.getMapper(CommentMapper.class);
        int t = commentMapper.insertComment(map);
        session.commit();
        session.close();
        return t;
    }

    @Override
    public List<Comment> getCommentsByNewsId(int newsId) {

        SqlSession session = SqlLink.getSqlSessionFactory().openSession();
        CommentMapper commentMapper = session.getMapper(CommentMapper.class);
        List<Comment> rows = commentMapper.getAllByNewsIdComments(newsId, false);
        session.commit();
        session.close();

        List<Comment> comments = new ArrayList<>();
        Map<Long, Comment> commentMap = new ConcurrentHashMap<>(); //保存数据，方便查询，去重 ，使用ConcurrentHashMap是为了解决线程安全问题，防止引发ConcurrentModificationException
        for (Comment comment : rows) commentMap.put(comment.getId(), comment);

        //处于所有关联
        for (Comment comment : rows)
            if (comment.getReplyId() != null) {
                List<Comment> replies = commentMap.get(comment.getId()).getReplies();  //注意:这里必须要以Map中数据为准（List中同一Comment对象可能存在多个）
                if (replies == null) {
                    replies = new ArrayList<>();
                    commentMap.get(comment.getId()).setReplies(replies); //注意：存入Map中的唯一对象
                }
                replies.add(commentMap.get(comment.getReplyId())); // 加入到回复列表中
            }

        //移除回复的评论（只保留只评价）
        for (Long id : commentMap.keySet()) {
            Comment comment = commentMap.get(id);
            if (comment.getReplyFor() != null) commentMap.remove(comment.getId());
        }

        //生成最终结果数据
        for (Long id : commentMap.keySet()) comments.add(commentMap.get(id));
        return comments;
    }

    @Override
    public int updateLikeNum(int commentId, boolean like) {
        SqlSession session = SqlLink.getSqlSessionFactory().openSession();
        CommentMapper commentMapper = session.getMapper(CommentMapper.class);
        commentMapper.updateLikeNum(commentId, like);
        int t = commentMapper.getLikeNum(commentId);
        session.commit();
        session.close();
        return t;
    }

    @Override
    public int updateDislikeNum(int commentId, boolean dislike) {
        SqlSession session = SqlLink.getSqlSessionFactory().openSession();
        CommentMapper commentMapper = session.getMapper(CommentMapper.class);
        commentMapper.updateDislikeNum(commentId, dislike);
        int t = commentMapper.getDisLikeNum(commentId);
        session.commit();
        session.close();
        return t;
    }
}