package com.training.controller;

import com.training.model.Book;
import com.training.model.BuyBook;
import com.training.model.Client;
import com.training.service.BookServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

/**
 * Created by Mschneider on 04-06-17.
 */

public class BuyBookRestControllerTest {
    @Test
    public void testCreateViewDeleteUpdate() {
        //added
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/api/buyBook";
        Book book = new Book(20, 20);
        Client client = new Client("mikesc", "123", "Schneider", "Rue de jolimont", "mike17sc@hotmail.com");
        ResponseEntity<Book> responseEntity = restTemplate.postForEntity("http://localhost:8080/api/book", book, Book.class);
        ResponseEntity<Client> responseEntity1 = restTemplate.postForEntity("http://localhost:8080/api/client", client, Client.class);
        BuyBook buyBook = new BuyBook(responseEntity1.getBody(), responseEntity.getBody(), 2, "Home");
        ResponseEntity<BuyBook> responseEntity2 = restTemplate.postForEntity(fooResourceUrl, buyBook, BuyBook.class);
        Assertions.assertThat(responseEntity2.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        System.out.println("buybook added...");

        //listed
        ResponseEntity<String> response3 = restTemplate.getForEntity(fooResourceUrl, String.class);
        System.out.println(response3.getBody());
        Assertions.assertThat(response3.getStatusCode()).isEqualTo(HttpStatus.OK);

        //updated
        responseEntity.getBody().setQuantity(99);
        restTemplate.put(fooResourceUrl + "/" + responseEntity.getBody().getId(), responseEntity.getBody(), BuyBook.class);
        System.out.println("Buybook updated");

        //deleted
        restTemplate.delete(fooResourceUrl + "/" + responseEntity.getBody().getId());
        System.out.println("User deleted");
    }

    @Test
    public void testTotalBookSold() {
        //Add buy book
        RestTemplate restTemplate = new RestTemplate();
        Book book = new Book(20, 20);
        Client client = new Client("mikesc", "123", "Schneider", "Rue de jolimont", "mike17sc@hotmail.com");
        ResponseEntity<Book> responseEntity = restTemplate.postForEntity("http://localhost:8080/api/book", book, Book.class);
        ResponseEntity<Client> responseEntity1 = restTemplate.postForEntity("http://localhost:8080/api/client", client, Client.class);
        BuyBook buyBook = new BuyBook(responseEntity1.getBody(), responseEntity.getBody(), 2, "Home");
        restTemplate.postForEntity("http://localhost:8080/api/buyBook", buyBook, BuyBook.class);
        restTemplate.postForEntity("http://localhost:8080/api/buyBook", buyBook, BuyBook.class);
        System.out.println("buybook added...");

        // test Total
        ResponseEntity<Integer> responseEntity3 = restTemplate.getForEntity("http://localhost:8080/api/buyBook/totalBookSold", Integer.class);
        Assertions.assertThat(responseEntity3.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity3.getBody()).isEqualTo(4);

    }

    @Test
    public void bestSeller() {
        RestTemplate restTemplate = new RestTemplate();
        Book book = new Book(30, 30);
        Client client = new Client("mikesch", "123", "Schneider", "Rue de jolimont", "mike17sc@hotmail.com");
        ResponseEntity<Book> responseEntity = restTemplate.postForEntity("http://localhost:8080/api/book", book, Book.class);
        ResponseEntity<Client> responseEntity1 = restTemplate.postForEntity("http://localhost:8080/api/client", client, Client.class);
        BuyBook buyBook = new BuyBook(responseEntity1.getBody(), responseEntity.getBody(), 5, "Home");
        restTemplate.postForEntity("http://localhost:8080/api/buyBook", buyBook, BuyBook.class);
        restTemplate.postForEntity("http://localhost:8080/api/buyBook", buyBook, BuyBook.class);
        System.out.println("buybook added...");


    }

}
