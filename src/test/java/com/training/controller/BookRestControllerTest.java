package com.training.controller;

import com.training.model.Book;
import com.training.service.BookServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

/**
 * Created by Mschneider on 04-06-17.
 */

public class BookRestControllerTest {
    @Test
    public void testCreateViewDeleteUpdate() {
        //added
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/api/book";
        Book book = new Book(20, 20);
        ResponseEntity<Book> responseEntity = restTemplate.postForEntity(fooResourceUrl, book, Book.class);
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        System.out.println("book added...");

        //listed
        ResponseEntity<String> response1 = restTemplate.getForEntity(fooResourceUrl, String.class);
        System.out.println(response1.getBody());
        Assertions.assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.OK);

        //updated
        responseEntity.getBody().setQuantity(30);
        restTemplate.put(fooResourceUrl + "/" + responseEntity.getBody().getId(), responseEntity.getBody(), Book.class);
        System.out.println("Book updated");

        //deleted
        restTemplate.delete(fooResourceUrl + "/" + responseEntity.getBody().getId());
        System.out.println("Book deleted");
    }
}
