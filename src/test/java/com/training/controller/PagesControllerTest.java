package com.training.controller;

import com.training.model.Pages;
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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

/**
 * Created by Mschneider on 04-06-17.
 */
public class PagesControllerTest {
    @Test
    public void testCreateViewDeleteUpdate() {
        //added

        RestTemplate restTemplate = new RestTemplate();
        Book book=new Book(50,50);
        ResponseEntity<Book> responseEntity1=restTemplate.postForEntity("http://localhost:8080/api/book",book,Book.class);
        String fooResourceUrl = "http://localhost:8080/api/pages";
        Pages pages = new Pages(25,"Heroes",responseEntity1.getBody());
        ResponseEntity<Pages> responseEntity = restTemplate.postForEntity(fooResourceUrl, pages, Pages.class);
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        System.out.println("pages added...");

        //listed
        ResponseEntity<String> response1 = restTemplate.getForEntity(fooResourceUrl, String.class);
        System.out.println(response1.getBody());
        Assertions.assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.OK);

        //updated
        responseEntity.getBody().setTitle("moidhjsfomqdhfsqfdsh");
        restTemplate.put(fooResourceUrl + "/" + responseEntity.getBody().getId(), responseEntity.getBody(), Pages.class);
        System.out.println("Pages updated");

        //deleted
        restTemplate.delete(fooResourceUrl + "/" + responseEntity.getBody().getId());
        System.out.println("User deleted");
    }

    @Test
    public void testCreateNull() throws Exception {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String fooResourceUrl = "http://localhost:8080/api/pages";
            Pages pages = new Pages();
            ResponseEntity<Pages> responseEntity = restTemplate.postForEntity(fooResourceUrl, pages, Pages.class);
            Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (HttpClientErrorException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("409 null");
        }

    }
}
