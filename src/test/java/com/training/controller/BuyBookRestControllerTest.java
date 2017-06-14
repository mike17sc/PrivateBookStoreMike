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
    public void testCreateViewDelete() {
        RestTemplate restTemplate = new RestTemplate();
        Book book = new Book(20,20);
        Client client=new Client("mikesc","123","Schneider","Rue de jolimont","mike17sc@hotmail.com");
        ResponseEntity<Book> responseEntity = restTemplate.postForEntity("http://localhost:8080/api/book", book, Book.class);
        ResponseEntity<Client> responseEntity1 = restTemplate.postForEntity("http://localhost:8080/api/client", client, Client.class);
        BuyBook buyBook=new BuyBook(responseEntity1.getBody(),responseEntity.getBody(),20,"Home");
        ResponseEntity<BuyBook> responseEntity2 = restTemplate.postForEntity("http://localhost:8080/api/buybook", buyBook, BuyBook.class);
        Assertions.assertThat(responseEntity2.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        System.out.println("book added...");
        ResponseEntity<String> responseEntity3= restTemplate.getForEntity("http://localhost:8080/api/buybook",String.class);

    }
}
