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
        ResponseEntity<Book> responseEntity=restTemplate.postForEntity("http://localhost:8080/api/book", book, Book.class);
        Book book1 = new Book(40, 50);
        restTemplate.postForEntity("http://localhost:8080/api/book", book1, Book.class);
        Book book2 = new Book(60, 80);
        restTemplate.postForEntity("http://localhost:8080/api/book", book2, Book.class);
        Pages pages = new Pages(25,"Heroes",responseEntity.getBody());
        restTemplate.postForEntity("http://localhost:8080/api/pages", pages, Pages.class);
        Pages pages2 = new Pages(12,"House",responseEntity.getBody());
        restTemplate.postForEntity("http://localhost:8080/api/pages", pages, Pages.class);
        Pages pages3 = new Pages(37,"Heroes",responseEntity.getBody());
        restTemplate.postForEntity("http://localhost:8080/api/pages", pages, Pages.class);
        Client client = new Client("mikesc","123","Schneider","jolimont","mike17sc@hotmail.com");
        ResponseEntity<Client> responseEntity1=restTemplate.postForEntity("http://localhost:8080/api/client", client, Client.class);
        Admin admin = new Admin("mikesch","123","Schneider");
        ResponseEntity<Admin> adminResponseEntity1=restTemplate.postForEntity("http://localhost:8080/api/admin", admin, Admin.class);
        BuyBook buyBook = new BuyBook(responseEntity1.getBody(), responseEntity.getBody(), 5, "Home");
        restTemplate.postForEntity("http://localhost:8080/api/buyBook", buyBook, BuyBook.class);
        restTemplate.postForEntity("http://localhost:8080/api/buyBook", buyBook, BuyBook.class);

    }
}
