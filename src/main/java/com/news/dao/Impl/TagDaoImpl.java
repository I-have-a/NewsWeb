package com.news.dao.Impl;

import com.news.dao.Template;
import com.news.model.Tag;
import mybatis.TagMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class TagDaoImpl extends BaseDao implements Template<Tag> {
    @Override
    public List<Tag> getAll() {
        SqlSession session = null;
        try {
            session = getSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
        TagMapper tagMapper = session.getMapper(TagMapper.class);
        List<Tag> tags = tagMapper.selectTag();
        session.close();
        return tags;
    }

    @Override
    public List<Tag> getWhere(int id) {
        return null;
    }

    @Override
    public Tag getOne(int id) {
        return null;
    }
}
