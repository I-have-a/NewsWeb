package com.news.Tool;

import com.news.model.Comment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentUtil {
    /**
     * 将评论列表转为Map（解决jsp:param不能传递对象的问题）
     *
     * @param comments 评论集合
     * @return 单列变双列
     */
    public static Map<Long, Comment> toMap(List<Comment> comments) {
        Map<Long, Comment> commentMap = new HashMap<>();
        for (Comment comment : comments) {
            commentMap.put(comment.getId(), comment);

            List<Comment> replies = comment.getReplies();
            if (replies != null && replies.size() > 0) {
                commentMap.putAll(toMap(replies)); //递归调用
            }
        }

        return commentMap;
    }
}
