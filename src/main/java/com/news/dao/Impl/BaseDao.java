package com.news.dao.Impl;

import java.util.List;

public abstract class BaseDao<T> {

    public abstract List<T> getAll();

    public abstract List<T> getWhere(int id);

    public abstract T getOne(int id);
}