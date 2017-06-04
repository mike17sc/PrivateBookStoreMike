package com.training.service;

import com.training.model.Content;

import java.util.Collection;

/**
 * Created by Mschneider on 04-06-17.
 */
public interface ContentService {
    Collection<Content> list();

    Content get(Long id);

    Content create(Content content);

    Content update(Content content);

    boolean delete(Long id);
}
