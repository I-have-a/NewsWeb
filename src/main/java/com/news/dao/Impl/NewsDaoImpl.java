package com.news.dao.Impl;

import com.news.Tool.TimeFormat;
import com.news.dao.Template;
import com.news.model.News;
import mybatis.NewsMapper;
import mybatis.TwoMapper;
import org.apache.ibatis.session.SqlSession;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class NewsDaoImpl extends BaseDao implements Template<News> {

    public String pundateToString(News news) throws ParseException {
        DateFormat parse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return new TimeFormat().getInterval(parse.parse(parse.format(news.getPubdate())));
    }

    @Override
    public List<News> getAll() {
        SqlSession session = null;
        try {
            session = getSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
        NewsMapper newsMapper = session.getMapper(NewsMapper.class);
        List<News> news = newsMapper.selectNews();
        session.close();
        return news;
    }

    @Override
    public List<News> getWhere(int id) {
        SqlSession session = null;
        try {
            session = getSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
        NewsMapper newsMapper = session.getMapper(NewsMapper.class);
        List<News> news = newsMapper.selectWhere(id);
        session.close();
        return news;
    }

    @Override
    public News getOne(int id) {
        SqlSession session = null;
        try {
            session = getSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
        NewsMapper newsMapper = session.getMapper(NewsMapper.class);
        News news = newsMapper.getDetails(id);
        session.close();
        return news;
    }

    public List<News> getTagNews(int id) {
        SqlSession session = null;
        try {
            session = getSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
        TwoMapper newsMapper = session.getMapper(TwoMapper.class);
        List<News> news = newsMapper.getTagNews(id);
        session.close();
        return news;
    }
}
