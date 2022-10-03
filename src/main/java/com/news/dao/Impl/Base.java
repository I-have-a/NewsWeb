package com.news.dao.Impl;

import java.util.List;

public interface Base<T> {

    /**
     * 无过滤获得列表
     * @return list列表
     */
    List<T> getAll();

    /**
     * 单条件查询列表
     * @param k 要查询的字段
     * @param v 值
     * @param <V> 不定类型
     * @return list列表
     */
    <V> List<T> getSingleWhere(String k, V v);

    /**
     * 单条件查询
     * @param k 要用的字段
     * @param v 值
     * @param <V> 泛型，达成多态
     * @return <T> 跟从类的泛型
     */
    <V> T getSingleOne(String k , V v);

    /**
     * 添加信息
     * @param t 所用的值，map
     * @return 影响的行数
     */
    int insert(T t);
}
