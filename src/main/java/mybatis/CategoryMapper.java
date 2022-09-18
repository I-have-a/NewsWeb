package mybatis;

import com.news.model.Category;

import java.util.List;

public interface CategoryMapper {
    List<Category> selectCategory();
}
