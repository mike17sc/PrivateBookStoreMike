package com.training.service;

import com.training.dao.BookRepository;
import com.training.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by Mschneider on 04-06-17.
 */
@Component
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Collection<Book> list() {
        Collection<Book> books = bookRepository.findAll();
        return books;
    }

    @Override
    public Book get(Long id) {
        return bookRepository.findOne(id);
    }

    @Override
    public Book create(Book book) {
        if (book.getId() != null) {
            return null;
        }
        else {
            if(book.getQuantity()<=0){
                book.setStatus("OUT OF STOCK");
            }
            else{
                book.setStatus("AVAILABLE");
            }
            return bookRepository.save(book);
        }
    }

    @Override
    public Book update(Book book) {
        if (book.getId() == null) {
            return null;
        } else {
            if (book.getId() == null) {
                return null;
            }
            else {
                if(book.getQuantity()<=0){
                    book.setStatus("OUT OF STOCK");
                }
                else{
                    book.setStatus("AVAILABLE");
                }
            }
            return bookRepository.save(book);
        }
    }

    @Override
    public boolean delete(Long id) {
        bookRepository.delete(id);
        return true;
    }
}
