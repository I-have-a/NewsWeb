package com.news.dao;

import java.util.List;

public interface Template<T> {

    List<T> getAll();

    List<T> getWhere(int id);

    T getOne(int id);
}
