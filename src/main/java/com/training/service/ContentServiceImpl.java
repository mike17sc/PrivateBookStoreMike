package com.training.service;

import com.training.dao.ContentRepository;
import com.training.model.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;

/**
 * Created by Mschneider on 04-06-17.
 */
@Component
public class ContentServiceImpl implements ContentService {
    @Autowired
    private ContentRepository contentRepository;

    @Override
    public Collection<Content> list() {
        Collection<Content> content = contentRepository.findAll();
        return content;
    }

    @Override
    public Content get(Long id) {

        return contentRepository.findOne(id);
    }

    @Override
    public Content create(Content content) {
        if (content.getId() != null) {
            return null;
        } else {
            content.setLastUpdateDate(new Date());
            return contentRepository.save(content);
        }
    }

    @Override
    public Content update(Content content) {
        if (content.getId() == null) {
            return null;
        } else {
            content.setLastUpdateDate(new Date());
            return contentRepository.save(content);
        }
    }

    @Override
    public boolean delete(Long id) {
        contentRepository.delete(id);
        return true;
    }
}
