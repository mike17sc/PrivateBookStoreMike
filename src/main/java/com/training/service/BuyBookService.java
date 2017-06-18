package com.training.service;

import com.training.model.Book;
import com.training.model.BuyBook;
import com.training.model.Client;

import java.util.Collection;
import java.util.List;

/**
 * Created by Mschneider on 04-06-17.
 */
public interface BuyBookService {
    Collection<BuyBook> list();

    BuyBook get(Long id);

    BuyBook create(BuyBook buyBook) throws MyException;

    BuyBook update(BuyBook buyBook);

    boolean delete(Long id);

    int totalBookSold();

    Book bestSeller();

    Client bestBuyer();




}
