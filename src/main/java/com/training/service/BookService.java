package com.training.service;

import com.training.model.Admin;
import com.training.model.Book;

import java.util.Collection;

/**
 * Created by Mschneider on 04-06-17.
 */
public interface BookService {
    Collection<Book>list();
    Book get(Long id);
    Book create(Book book);
    Book update(Book book);
    boolean delete(Long id);
}
