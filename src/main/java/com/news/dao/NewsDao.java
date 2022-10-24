package com.news.dao;

import com.news.model.News;

import java.util.List;

public interface NewsDao {
    String pundateToString(News news);

    <E> List<News> getSingleWhere(String k, E e);

    List<News> getTagNews(int id);

    List<News> RecommendNews();
}
