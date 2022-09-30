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
import java.util.HashMap;
import java.util.List;

public class NewsDaoImpl implements Base<News> {

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
        HashMap map = new HashMap();
        SqlSession session = SqlLink.getSqlSessionFactory().openSession();
        NewsMapper newsMapper = session.getMapper(NewsMapper.class);
        List<News> news = newsMapper.selectNews(map);
        session.close();
        return news;
    }

    @Override
    public <E> List<News> getWhere(E e) {
        HashMap map = new HashMap();
        map.put("categoryId", e);
        SqlSession session = SqlLink.getSqlSessionFactory().openSession();
        NewsMapper newsMapper = session.getMapper(NewsMapper.class);
        List<News> news = newsMapper.selectNews(map);
        session.close();
        return news;
    }

    @Override
    public <E> News getOne(E id) {
        HashMap map = new HashMap();
        map.put("id", id);
        SqlSession session = SqlLink.getSqlSessionFactory().openSession();
        NewsMapper newsMapper = session.getMapper(NewsMapper.class);
        List<News> news = newsMapper.selectNews(map);
        session.close();
        return news.get(0);
    }

    @Override
    public int insert(HashMap map) {
        return 0;
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
