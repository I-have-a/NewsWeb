package com.news.Impl;

import com.news.Tool.SqlLink;
import com.news.dao.TagDao;
import com.news.model.Tag;
import mybatis.TagMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class TagDaoImpl implements TagDao {
    @Override
    public List<Tag> getAll() {
        SqlSession session = SqlLink.getSqlSessionFactory().openSession();
        TagMapper tagMapper = session.getMapper(TagMapper.class);
        List<Tag> tags = tagMapper.selectTag();
        session.close();
        return tags;
    }

    @Override
    public List<Tag> getByNewsIdTag(int newsId) {
        SqlSession session = SqlLink.getSqlSessionFactory().openSession();
        TagMapper tagMapper = session.getMapper(TagMapper.class);
        List<Tag> tags = tagMapper.selectOne(newsId);
        session.close();
        return tags;
    }
}
