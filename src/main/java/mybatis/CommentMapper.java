package mybatis;

import com.news.model.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommentMapper {

    int insertComment(Map map);

    List<Comment> getAllByNewsIdComments(@Param("newsId") int newsId, @Param("hidden") boolean hidden);

    int updateLikeNum(@Param("id") int id, @Param("likeNum") boolean likeNum);

    int updateDislikeNum(@Param("id") int id, @Param("dislikeNum") boolean dislikeNum);

    int getLikeNum(@Param("id") int id);

    int getDisLikeNum(@Param("id") int id);
}
