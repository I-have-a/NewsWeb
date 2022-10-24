package com.news.dao;

import com.news.model.Tag;

import java.util.List;

public interface TagDao {
    List<Tag> getAll();

    List<Tag> getByNewsIdTag(int newsId);
}
