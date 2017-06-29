package com.training.controller;

import com.training.model.*;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Mschneider on 16-06-17.
 */

public class InitDbTest {
    @Test
    public void testInitDb(){
        RestTemplate restTemplate=new RestTemplate();
        Book book = new Book(20, 20);
        ResponseEntity<Book> bookResponseEntity=restTemplate.postForEntity("http://localhost:8080/api/book", book, Book.class);
        Book book1 = new Book(40, 50);
        ResponseEntity<Book> book1ResponseEntity=restTemplate.postForEntity("http://localhost:8080/api/book", book1, Book.class);
        Book book2 = new Book(60, 80);
        restTemplate.postForEntity("http://localhost:8080/api/book", book2, Book.class);
        Pages pages = new Pages(25,"Heroes",bookResponseEntity.getBody());
        restTemplate.postForEntity("http://localhost:8080/api/pages", pages, Pages.class);
        Pages pages2 = new Pages(12,"House",book1ResponseEntity.getBody());
        restTemplate.postForEntity("http://localhost:8080/api/pages", pages2, Pages.class);
        Pages pages3 = new Pages(37,"Heroes",book1ResponseEntity.getBody());
        restTemplate.postForEntity("http://localhost:8080/api/pages", pages3, Pages.class);
        Client client = new Client("mikesc","123","Schneider","jolimont","mike17sc@hotmail.com");
        ResponseEntity<Client> clientResponseEntity=restTemplate.postForEntity("http://localhost:8080/api/client", client, Client.class);
        Client client1 = new Client("mikeschne","123","Schneiderfefe","jolimontsdfs","mike17sc@hotmail.com");
        ResponseEntity<Client> clientResponseEntity1=restTemplate.postForEntity("http://localhost:8080/api/client", client1, Client.class);
        Admin admin = new Admin("mikesch","123","Schneider");
        ResponseEntity<Admin> adminResponseEntity1=restTemplate.postForEntity("http://localhost:8080/api/admin", admin, Admin.class);
        BuyBook buyBook = new BuyBook(clientResponseEntity.getBody(), bookResponseEntity.getBody(), 5, "Home");
        restTemplate.postForEntity("http://localhost:8080/api/buyBook", buyBook, BuyBook.class);
        BuyBook buyBook2 = new BuyBook(clientResponseEntity1.getBody(), book1ResponseEntity.getBody(), 10, "Home");
        restTemplate.postForEntity("http://localhost:8080/api/buyBook", buyBook2, BuyBook.class);
        restTemplate.postForEntity("http://localhost:8080/api/buyBook", buyBook2, BuyBook.class);
        restTemplate.postForEntity("http://localhost:8080/api/buyBook", buyBook2, BuyBook.class);

    }
}
