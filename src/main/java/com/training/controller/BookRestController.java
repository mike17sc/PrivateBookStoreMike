package com.training.controller;

import com.training.model.Book;
import com.training.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by Mschneider on 04-06-17.
 */
@RestController
public class BookRestController {
    @Autowired
    private BookServiceImpl bookServiceImpl;

    @GetMapping("api/book")
    public Collection<Book> getBooks(){
        return bookServiceImpl.list();
    }
    @GetMapping("api/book/{id}")
    public ResponseEntity getBook(@PathVariable("id")Long id){
        Book book= bookServiceImpl.get(id);
        if (book==null){
            return new ResponseEntity("No book found for ID" + id, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity(book,HttpStatus.OK);
        }
    }
    @PostMapping(value = "api/book")
    public ResponseEntity createCustomer(@RequestBody Book book) {

        bookServiceImpl.create(book);

        return new ResponseEntity(book, HttpStatus.CREATED);
    }

    @DeleteMapping("api/book/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Long id) {

        if (!bookServiceImpl.delete(id)) {
            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(id, HttpStatus.OK);

    }

    @PutMapping("api/book/{id}")
    public ResponseEntity updateCustomer(@PathVariable Long id, @RequestBody Book book) {

        book = bookServiceImpl.update(book);

        if (book==null) {
            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(book, HttpStatus.OK);
    }
}
