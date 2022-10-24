package mybatis;

import com.news.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserMapper {
    User getUser(Map map);

    List<User> getAllUser();

    int insertUser(User user);

    int updateOne(User user);
}
