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
        ResponseEntity<Book> bookResponseEntity = restTemplate.postForEntity("http://localhost:8080/api/book", book, Book.class);
        ResponseEntity<Client> clientResponseEntity = restTemplate.postForEntity("http://localhost:8080/api/client", client, Client.class);
        BuyBook buyBook = new BuyBook(clientResponseEntity.getBody(), bookResponseEntity.getBody(), 2, "Home");
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
        Book book2 = new Book(40, 50);
        Client client = new Client("mikesch", "123", "Schneider", "Rue de jolimont", "mike17sc@hotmail.com");
        ResponseEntity<Book> booResponseEntity = restTemplate.postForEntity("http://localhost:8080/api/book", book, Book.class);
        ResponseEntity<Book> book2ResponseEntity = restTemplate.postForEntity("http://localhost:8080/api/book", book2, Book.class);
        ResponseEntity<Client> clientResponseEntity = restTemplate.postForEntity("http://localhost:8080/api/client", client, Client.class);
        BuyBook buyBook = new BuyBook(clientResponseEntity.getBody(), booResponseEntity.getBody(), 6, "Home");
        BuyBook buyBook2 = new BuyBook(clientResponseEntity.getBody(), book2ResponseEntity.getBody(), 10, "Home");
        restTemplate.postForEntity("http://localhost:8080/api/buyBook", buyBook, BuyBook.class);
        restTemplate.postForEntity("http://localhost:8080/api/buyBook", buyBook2, BuyBook.class);
        restTemplate.postForEntity("http://localhost:8080/api/buyBook", buyBook, BuyBook.class);
        System.out.println("buybook added...");
        ResponseEntity<Book> bestSellerResponseEntity=restTemplate.getForEntity("http://localhost:8080/api/buyBook/bestSeller",Book.class);
        Assertions.assertThat(bestSellerResponseEntity.getBody().getId()).isEqualTo(booResponseEntity.getBody().getId());
    }
    @Test
    public void bestClient() {
        RestTemplate restTemplate = new RestTemplate();
        Book book = new Book(30, 30);
        Client client = new Client("mikesch", "123", "Schneider", "Rue de jolimont", "mike17sc@hotmail.com");
        Client client2 = new Client("cath", "456", "Berki", "Rue jolimont", "cath@hotmail.com");
        ResponseEntity<Book> booResponseEntity = restTemplate.postForEntity("http://localhost:8080/api/book", book, Book.class);
        ResponseEntity<Client> clientResponseEntity = restTemplate.postForEntity("http://localhost:8080/api/client", client, Client.class);
        ResponseEntity<Client> client2ResponseEntity = restTemplate.postForEntity("http://localhost:8080/api/client", client2, Client.class);
        BuyBook buyBook = new BuyBook(clientResponseEntity.getBody(), booResponseEntity.getBody(), 6, "Home");
        BuyBook buyBook2 = new BuyBook(client2ResponseEntity.getBody(), booResponseEntity.getBody(), 10, "Home");
        restTemplate.postForEntity("http://localhost:8080/api/buyBook", buyBook, BuyBook.class);
        restTemplate.postForEntity("http://localhost:8080/api/buyBook", buyBook2, BuyBook.class);
        restTemplate.postForEntity("http://localhost:8080/api/buyBook", buyBook, BuyBook.class);
        System.out.println("buybook added...");
        ResponseEntity<Client> bestBuyerResponseEntity=restTemplate.getForEntity("http://localhost:8080/api/buyBook/bestBuyer",Client.class);
        Assertions.assertThat(bestBuyerResponseEntity.getBody().getId()).isEqualTo(clientResponseEntity.getBody().getId());
    }


}
