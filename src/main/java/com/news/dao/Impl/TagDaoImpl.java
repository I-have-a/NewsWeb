package com.news.dao.Impl;

import com.news.Tool.SqlLink;
import com.news.model.Tag;
import mybatis.TagMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class TagDaoImpl implements Base<Tag> {
    @Override
    public List<Tag> getAll() {
        SqlSession session = SqlLink.getSqlSessionFactory().openSession();
        TagMapper tagMapper = session.getMapper(TagMapper.class);
        List<Tag> tags = tagMapper.selectTag();
        session.close();
        return tags;
    }

    @Override
    public <E> List<Tag> getSingleWhere(String k, E e) {
        return null;
    }

    @Override
    public <E> Tag getSingleOne(String k , E e) {
        return null;
    }

    @Override
    public int insert(Tag tag) {
        return 0;
    }

    @Override
    public boolean update(Tag tag) {
        return false;
    }
}
