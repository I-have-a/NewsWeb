package com.news.dao.Impl;

import com.news.Tool.SqlLink;
import com.news.model.User;
import mybatis.UserMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;

public class UserDaoImpl implements Base<User> {
    @Override
    public List<User> getAll() {
        SqlSession session = SqlLink.getSqlSessionFactory().openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        List<User> users = userMapper.getAllUser();
        session.close();
        return users;
    }

    @Override
    public <E> List<User> getSingleWhere(String k, E e) {
        return null;
    }


    @Override
    public <E> User getSingleOne(String k , E e) {
        HashMap map = new HashMap();
        map.put(k, e);
        SqlSession session = SqlLink.getSqlSessionFactory().openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.getUser(map);
        session.close();
        return user;
    }

    @Override
    public int insert(User user) {
        SqlSession session = SqlLink.getSqlSessionFactory().openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        int t = userMapper.insertUser(user);
        session.commit();
        session.close();
        return t;
    }
}
