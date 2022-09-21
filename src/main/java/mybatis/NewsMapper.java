package mybatis;

import com.news.model.News;

import java.util.List;

public interface NewsMapper {
    List<News> selectNews();

    List<News> selectWhere(int id);

    News getDetails(int id);

    List<News> Recommend();
}
