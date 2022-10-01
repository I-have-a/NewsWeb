package mybatis;

import com.news.model.User;

import java.util.HashMap;
import java.util.List;

public interface UserMapper {
    User getUser(HashMap map);

    List<User> getAllUser();

    int insertUser(User user);
}
