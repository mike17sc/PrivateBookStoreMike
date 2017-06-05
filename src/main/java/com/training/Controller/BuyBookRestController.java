package com.training.Controller;

import com.training.model.Book;
import com.training.model.BuyBook;
import com.training.service.BookServiceImpl;
import com.training.service.BuyBookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by Mschneider on 04-06-17.
 */
@RestController
public class BuyBookRestController {
    @Autowired
    private BuyBookServiceImpl buyBookServiceImpl;
    @Autowired
    private BookServiceImpl bookServiceImpl;

    @GetMapping("/buyBook")
    public Collection<BuyBook> getBuyBooks(){
        return buyBookServiceImpl.list();
    }
    @GetMapping("/buyBook/{id}")
    public ResponseEntity getBuyBook(@PathVariable("id")Long id){
        BuyBook buyBook= buyBookServiceImpl.get(id);
        if (buyBook==null){
            return new ResponseEntity("No buyBook found for ID" + id, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity(buyBook,HttpStatus.OK);
        }
    }
    @PostMapping(value = "/buybook")
    public ResponseEntity createBuybook(@RequestBody BuyBook buyBook) {

        buyBookServiceImpl.create(buyBook);
        Book book= buyBook.getBook();
        book.setQuantity(book.getQuantity()-1);
        bookServiceImpl.update(book);


        return new ResponseEntity(buyBook, HttpStatus.OK);
    }

    @DeleteMapping("/buybook/delete/{id}")
    public ResponseEntity deleteBuybook(@PathVariable Long id) {

        if (!buyBookServiceImpl.delete(id)) {
            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(id, HttpStatus.OK);

    }

    @PutMapping("/buybook/update/{id}")
    public ResponseEntity updateBuybook(@PathVariable Long id, @RequestBody BuyBook buyBook) {

        buyBook = buyBookServiceImpl.update(buyBook);


        if (null == buyBook) {
            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(buyBook, HttpStatus.OK);
    }
}
