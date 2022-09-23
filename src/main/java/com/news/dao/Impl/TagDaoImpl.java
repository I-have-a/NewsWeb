package com.news.dao.Impl;

import com.news.Tool.SqlLink;
import com.news.model.Tag;
import mybatis.TagMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
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
    public <E> List<Tag> getWhere(E e) {
        return null;
    }

    @Override
    public <E> Tag getOne(E e) {
        return null;
    }

    @Override
    public void insert(HashMap map) {

    }
}
