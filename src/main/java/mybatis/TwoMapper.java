package mybatis;

import com.news.model.News;

import java.util.List;

public interface TwoMapper {
    List<News> getTagNews(int id);
}
