package com.training.controller;

import com.training.model.Book;
import com.training.model.BuyBook;
import com.training.model.Client;
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

    @GetMapping("api/buyBook")
    public Collection<BuyBook> getBuyBooks() {
        return buyBookServiceImpl.list();
    }

    @GetMapping("api/buyBook/{id}")
    public ResponseEntity getBuyBook(@PathVariable("id") Long id) {
        BuyBook buyBook = buyBookServiceImpl.get(id);
        if (buyBook == null) {
            return new ResponseEntity("No buyBook found for ID" + id, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(buyBook, HttpStatus.OK);
        }
    }

    @GetMapping("api/buyBook/totalBookSold")

    public ResponseEntity totalBookSold()
    {
        int bookSold=buyBookServiceImpl.totalBookSold();
        return new ResponseEntity(bookSold,HttpStatus.OK);
    }
    @GetMapping("api/buyBook/bestSeller")

    public ResponseEntity bestSeller()
    {
        Book bestSeller=buyBookServiceImpl.bestSeller();
        return new ResponseEntity(bestSeller,HttpStatus.OK);
    }
    @GetMapping("api/buyBook/bestBuyer")

    public ResponseEntity bestBuyer()
    {
        Client bestBuyer=buyBookServiceImpl.bestBuyer();
        return new ResponseEntity(bestBuyer,HttpStatus.OK);
    }

    @PostMapping(value = "api/buyBook")
    public ResponseEntity createBuybook(@RequestBody BuyBook buyBook) {
        if(buyBook == null){
            return new ResponseEntity(buyBook, HttpStatus.CONFLICT);
        }
        else if(buyBook.getClient()==null){
            return new ResponseEntity(buyBook, HttpStatus.CONFLICT);
        }
        else if(buyBook.getBook()==null){
            return new ResponseEntity(buyBook, HttpStatus.CONFLICT);
        }
        else{
            buyBookServiceImpl.create(buyBook);
            return new ResponseEntity(buyBook, HttpStatus.CREATED);
        }

    }

    @DeleteMapping("api/buyBook/{id}")
    public ResponseEntity deleteBuybook(@PathVariable Long id) {

        if (!buyBookServiceImpl.delete(id)) {
            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(id, HttpStatus.OK);

    }

    @PutMapping("api/buyBook/{id}")
    public ResponseEntity updateBuybook(@PathVariable Long id, @RequestBody BuyBook buyBook) {

        buyBook = buyBookServiceImpl.update(buyBook);


        if (null == buyBook) {
            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(buyBook, HttpStatus.OK);
    }
}
