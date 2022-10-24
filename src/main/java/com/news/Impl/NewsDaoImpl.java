package com.news.Impl;

import com.news.Tool.SqlLink;
import com.news.Tool.TimeFormat;
import com.news.dao.NewsDao;
import com.news.model.News;
import mybatis.NewsMapper;
import org.apache.ibatis.session.SqlSession;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class NewsDaoImpl implements NewsDao {

    /**
     * 修改新闻的日期属性
     * @param news 要修改的新闻对象
     * @return 从数据库获取的新闻发布日期
     */
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
    public <E> List<News> getSingleWhere(String k, E e) {
        HashMap<String,E> map = new HashMap<>();
        map.put(k, e);
        SqlSession session = SqlLink.getSqlSessionFactory().openSession();
        NewsMapper newsMapper = session.getMapper(NewsMapper.class);
        List<News> news = newsMapper.selectNews(map);
        session.close();
        return news;
    }

    @Override
    public List<News> getTagNews(int id) {
        SqlSession session = SqlLink.getSqlSessionFactory().openSession();
        NewsMapper newsMapper = session.getMapper(NewsMapper.class);
        List<News> news = newsMapper.getTagNews(id);
        session.close();
        return news;
    }

    @Override
    public List<News> RecommendNews() {
        SqlSession session = SqlLink.getSqlSessionFactory().openSession();
        NewsMapper newsMapper = session.getMapper(NewsMapper.class);
        List<News> news = newsMapper.Recommend();
        session.close();
        return news;
    }
}
