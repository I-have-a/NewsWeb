package com.news.dao;

import com.news.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAll();

    <V> User getSingleOne(String k, V v);

    int addUser(User user);

    boolean update(User user);
}
