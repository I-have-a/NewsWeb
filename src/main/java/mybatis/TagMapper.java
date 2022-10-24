package mybatis;

import com.news.model.Tag;

import java.util.List;

public interface TagMapper {
    List<Tag> selectTag();

    List<Tag> selectOne(int newsId);
}
