package com.news.dao.Impl;

import com.news.Tool.SqlLink;
import com.news.model.Category;
import mybatis.CategoryMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CategoryDaoImpl implements Base<Category> {
    @Override
    public List<Category> getAll() {
        SqlSession session = SqlLink.getSqlSessionFactory().openSession();
        CategoryMapper CategoryMapper = session.getMapper(CategoryMapper.class);
        List<Category> categories = CategoryMapper.selectCategory();
        session.close();
        return categories;
    }

    @Override
    public <E> List<Category> getSingleWhere(String k , E e) {
        return null;
    }

    @Override
    public <E> Category getSingleOne(String k, E e) {
        return null;
    }

    @Override
    public int insert(Category category) {
        return 0;
    }

    @Override
    public boolean update(Category category) {
        return false;
    }
}
