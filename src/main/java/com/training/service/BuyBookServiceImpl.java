package com.training.service;

import com.training.dao.BookRepository;
import com.training.dao.BuyBookRepository;
import com.training.model.Book;
import com.training.model.BuyBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by Mschneider on 04-06-17.
 */
@Component
public class BuyBookServiceImpl implements BuyBookService {
    @Autowired
    private BuyBookRepository buyBookRepository;
    @Autowired
    private BookServiceImpl bookServiceImpl;

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
    public BuyBook create(BuyBook buyBook){
        int newQuantity=buyBook.getBook().getQuantity()-buyBook.getQuantity();
        if (newQuantity<0){
            return null;
        }
        else {

            buyBook.getBook().setQuantity(newQuantity);
            bookServiceImpl.update(buyBook.getBook());
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
    @Override
    public int totalBookSold(){
        return buyBookRepository.totalBookSold();
    }
}
