package com.news.dao.Impl;

import com.news.model.News;

import java.util.List;

public interface Base<T> {
    List<T> getAll();

    <E> List<T> getWhere(E e);

    <E> T getOne(E e);
}
