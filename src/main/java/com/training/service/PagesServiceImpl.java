package com.training.service;

import com.training.dao.PagesRepository;
import com.training.model.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by Mschneider on 04-06-17.
 */
@Component
public class PagesServiceImpl implements PagesService {
    @Autowired
    private PagesRepository pagesRepository;

    @Override
    public Collection<Pages> list() {
        Collection<Pages> pages = pagesRepository.findAll();
        return pages;
    }

    @Override
    public Pages get(Long id) {

        return pagesRepository.findOne(id);
    }

    @Override
    public Pages create(Pages pages) {
        if (pages.getId() != null) {
            return null;
        } else {
            return pagesRepository.save(pages);
        }
    }

    @Override
    public Pages update(Pages pages) {
        if (pages.getId() == null) {
            return null;
        } else {
            return pagesRepository.save(pages);
        }
    }

    @Override
    public boolean delete(Long id) {
        pagesRepository.delete(id);
        return true;
    }
}
