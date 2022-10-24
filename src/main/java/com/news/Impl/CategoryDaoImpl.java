package com.news.Impl;

import com.news.Tool.SqlLink;
import com.news.dao.CategoryDao;
import com.news.model.Category;
import mybatis.CategoryMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public List<Category> getAll() {
        SqlSession session = SqlLink.getSqlSessionFactory().openSession();
        CategoryMapper CategoryMapper = session.getMapper(CategoryMapper.class);
        List<Category> categories = CategoryMapper.selectCategory();
        session.close();
        return categories;
    }
}
