package com.training.controller;

import com.training.model.Book;
import com.training.model.Pages;
import com.training.model.Pages;
import com.training.service.PagesServiceImpl;
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
public class PagesControllerTest {
    @Test
    public void testCreateViewDelete() {
        RestTemplate restTemplate = new RestTemplate();
        Book book=new Book(20,20);
        ResponseEntity<Book>responseEntity=restTemplate.postForEntity("http://localhost:8080/api/book",book,Book.class);
        Pages pages = new Pages(1,"The hero",responseEntity.getBody());
        ResponseEntity<Pages> responseEntity1 = restTemplate.postForEntity("http://localhost:8080/api/pages", pages, Pages.class);
        System.out.println(responseEntity.getBody().getId()+responseEntity.getBody().getQuantity()+responseEntity.getBody().getPrice());
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        System.out.println("pages added...");
    }
}
