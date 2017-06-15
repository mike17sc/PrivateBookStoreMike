package com.training.controller;

import com.training.model.Admin;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Mschneider on 04-06-17.
 */

public class AdminRestControllerTest {
    @Test
    public void testCreateViewDeleteUpdate() {
        //added
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/api/admin";
        Admin admin = new Admin("Mikesc", "123", "Schneider");
        ResponseEntity<Admin> responseEntity = restTemplate.postForEntity(fooResourceUrl, admin, Admin.class);
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        System.out.println("admin added...");

        //listed
        ResponseEntity<String> response1 = restTemplate.getForEntity(fooResourceUrl, String.class);
        System.out.println(response1.getBody());
        Assertions.assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.OK);

        //updated
        responseEntity.getBody().setName("moidhjsfomqdhfsqfdsh");
        restTemplate.put(fooResourceUrl + "/" + responseEntity.getBody().getId(), responseEntity.getBody(), Admin.class);
        System.out.println("Admin updated");

        //deleted
        restTemplate.delete(fooResourceUrl + "/" + responseEntity.getBody().getId());
        System.out.println("User deleted");
    }

    @Test
    public void testCreateNull() throws Exception {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String fooResourceUrl = "http://localhost:8080/api/admin";
            Admin admin = new Admin("", "", "");
            ResponseEntity<Admin> responseEntity = restTemplate.postForEntity(fooResourceUrl, admin, Admin.class);
            Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (HttpClientErrorException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("409 null");
        }

    }

    @Test
    public void testDouble() throws Exception {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String fooResourceUrl = "http://localhost:8080/api/admin";
            Admin admin = new Admin("Cath", "123", "Schneider");
            restTemplate.postForEntity(fooResourceUrl, admin, Admin.class);
            restTemplate.postForEntity(fooResourceUrl, admin, Admin.class);
        } catch (HttpClientErrorException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("409 null");
        }
    }

}
