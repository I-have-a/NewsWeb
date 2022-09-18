package com.news.dao.Impl;

import com.news.dao.Template;
import com.news.model.Category;
import mybatis.CategoryMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CategoryDaoImpl extends BaseDao implements Template<Category> {
    @Override
    public List<Category> getAll() {
        SqlSession session = null;
        try {
            session = getSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
        CategoryMapper CategoryMapper = session.getMapper(CategoryMapper.class);
        List<Category> categories = CategoryMapper.selectCategory();
        session.close();
        return categories;
    }

    @Override
    public List<Category> getWhere(int id) {
        return null;
    }

    @Override
    public Category getOne(int id) {
        return null;
    }
}
