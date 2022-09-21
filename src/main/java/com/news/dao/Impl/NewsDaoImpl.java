package com.news.dao.Impl;

import com.news.Tool.SqlLink;
import com.news.Tool.TimeFormat;
import com.news.model.News;
import mybatis.NewsMapper;
import mybatis.TwoMapper;
import org.apache.ibatis.session.SqlSession;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewsDaoImpl extends BaseDao<News> {

    public String pundateToString(News news) {
        DateFormat parse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date pubdate = news.getPubdate();
        String format = parse.format(pubdate);
        Date parse1 = null;
        try {
            parse1 = parse.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new TimeFormat().getInterval(parse1);
    }

    @Override
    public List<News> getAll() {
        SqlSession session = SqlLink.getSqlSessionFactory().openSession();
        NewsMapper newsMapper = session.getMapper(NewsMapper.class);
        List<News> news = newsMapper.selectNews();
        session.close();
        return news;
    }

    @Override
    public List<News> getWhere(int id) {
        SqlSession session = SqlLink.getSqlSessionFactory().openSession();
        NewsMapper newsMapper = session.getMapper(NewsMapper.class);
        List<News> news = newsMapper.selectWhere(id);
        session.close();
        return news;
    }

    @Override
    public News getOne(int id) {
        SqlSession session = SqlLink.getSqlSessionFactory().openSession();
        NewsMapper newsMapper = session.getMapper(NewsMapper.class);
        News news = newsMapper.getDetails(id);
        session.close();
        return news;
    }

    public List<News> getTagNews(int id) {
        SqlSession session = SqlLink.getSqlSessionFactory().openSession();
        TwoMapper newsMapper = session.getMapper(TwoMapper.class);
        List<News> news = newsMapper.getTagNews(id);
        session.close();
        return news;
    }

    public List<News> RecommendNews() {
        SqlSession session = SqlLink.getSqlSessionFactory().openSession();
        NewsMapper newsMapper = session.getMapper(NewsMapper.class);
        List<News> news = newsMapper.Recommend();
        session.close();
        return news;
    }
}
