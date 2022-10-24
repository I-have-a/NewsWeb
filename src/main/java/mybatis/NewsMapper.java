package mybatis;

import com.news.model.News;

import java.util.HashMap;
import java.util.List;

public interface NewsMapper {
    List<News> selectNews(HashMap map);

    List<News> Recommend();

    List<News> getTagNews(int id);
}
