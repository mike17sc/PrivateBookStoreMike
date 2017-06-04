package com.training.service;

import com.training.model.Pages;

import java.util.Collection;

/**
 * Created by Mschneider on 04-06-17.
 */
public interface PagesService {
    Collection<Pages> list();

    Pages get(Long id);

    Pages create(Pages pages);

    Pages update(Pages pages);

    boolean delete(Long id);
}
