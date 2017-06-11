package com.training.controller;

import com.training.model.Book;
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

public class BookRestControllerTest {
    @Test
    public void testCreateViewDelete() {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/api/book";
        Book book = new Book(50, 20, true);
        ResponseEntity<Book> responseEntity = restTemplate.postForEntity(fooResourceUrl, book, Book.class);
        System.out.println(responseEntity.getBody().getId()+responseEntity.getBody().getQuantity()+responseEntity.getBody().getPrice());
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        System.out.println("book added...");

        ResponseEntity<String> responseEntity1= restTemplate.getForEntity(fooResourceUrl,String.class);

    }
}
