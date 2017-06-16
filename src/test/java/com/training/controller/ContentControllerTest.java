package com.training.controller;

import com.training.model.Book;
import com.training.model.Content;
import com.training.model.Pages;
import com.training.service.ContentServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Date;

/**
 * Created by Mschneider on 04-06-17.
 */
public class ContentControllerTest {
    @Test
    public void testCreateViewDeleteUpdate() {
        //added
        RestTemplate restTemplate = new RestTemplate();
        Book book=new Book(50,50);
        ResponseEntity<Book> responseEntity1=restTemplate.postForEntity("http://localhost:8080/api/book",book,Book.class);
        Pages pages=new Pages(50,"Heroes",responseEntity1.getBody());
        ResponseEntity<Pages> responseEntity2=restTemplate.postForEntity("http://localhost:8080/api/pages",pages,Pages.class);
        String fooResourceUrl = "http://localhost:8080/api/content";
        Content content = new Content("This is content",new Date(),responseEntity2.getBody());
        ResponseEntity<Content> responseEntity = restTemplate.postForEntity(fooResourceUrl, content, Content.class);
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        System.out.println("content added...");

        //listed
        ResponseEntity<String> response1 = restTemplate.getForEntity(fooResourceUrl, String.class);
        System.out.println(response1.getBody());
        Assertions.assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.OK);

        //updated
        responseEntity.getBody().setText("moidhjsfomqdhfsqfdsh");
        restTemplate.put(fooResourceUrl + "/" + responseEntity.getBody().getId(), responseEntity.getBody(), Content.class);
        System.out.println("Content updated");

        //deleted
        restTemplate.delete(fooResourceUrl + "/" + responseEntity.getBody().getId());
        System.out.println("User deleted");
    }
}
