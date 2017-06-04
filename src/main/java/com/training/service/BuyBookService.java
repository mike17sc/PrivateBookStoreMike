package com.training.service;

import com.training.model.BuyBook;

import java.util.Collection;

/**
 * Created by Mschneider on 04-06-17.
 */
public interface BuyBookService {
    Collection<BuyBook> list();

    BuyBook get(Long id);

    BuyBook create(BuyBook buyBook);

    BuyBook update(BuyBook buyBook);

    boolean delete(Long id);
}
