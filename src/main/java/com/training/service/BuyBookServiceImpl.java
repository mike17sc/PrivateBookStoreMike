package com.training.service;

import com.training.dao.BuyBookRepository;
import com.training.model.BuyBook;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * Created by Mschneider on 04-06-17.
 */
public class BuyBookServiceImpl implements BuyBookService {
    @Autowired
    private BuyBookRepository buyBookRepository;

    @Override
    public Collection<BuyBook> list() {
        Collection<BuyBook> buyBooks = buyBookRepository.findAll();
        return buyBooks;
    }

    @Override
    public BuyBook get(Long id) {
        return buyBookRepository.findOne(id);
    }

    @Override
    public BuyBook create(BuyBook buyBook) {
        if (buyBook.getId() != null) {
            return null;
        } else {
            return buyBookRepository.save(buyBook);
        }
    }

    @Override
    public BuyBook update(BuyBook buyBook) {
        if (buyBook.getId() == null) {
            return null;
        } else {
            return buyBookRepository.save(buyBook);
        }
    }

    @Override
    public boolean delete(Long id) {
        buyBookRepository.delete(id);
        return true;
    }
}
