package com.news.Tool;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlLink {
    private static SqlSessionFactory build;

    static {
        try {
            String re = "mybatis-config.xml";
            InputStream resourceAsStream = Resources.getResourceAsStream(re);
            build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return build;
    }
}
