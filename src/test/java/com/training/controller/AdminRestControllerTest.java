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
    public void testCreateViewDelete() {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/api/admin";
        Admin admin = new Admin("Mikesc", "123", "Schneider");
        ResponseEntity<Admin> responseEntity = restTemplate.postForEntity(fooResourceUrl, admin, Admin.class);
        System.out.println(responseEntity.getBody().getId()+responseEntity.getBody().getUsername()+responseEntity.getBody().getName());
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        System.out.println("admin added...");
        ResponseEntity<String> responseEntity1= restTemplate.getForEntity(fooResourceUrl,String.class);
    }
    @Test
    public void testCreateNull() throws Exception {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String fooResourceUrl = "http://localhost:8080/api/admin";
            Admin admin = new Admin("", "", "");
            ResponseEntity<Admin> responseEntity = restTemplate.postForEntity(fooResourceUrl, admin, Admin.class);
            System.out.println(responseEntity.getBody());
            Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
            System.out.println("admin added...");
        } catch (HttpClientErrorException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("422 null");
        }

    }
    @Test
    public void testDouble() throws Exception {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String fooResourceUrl = "http://localhost:8080/api/admin";
            Admin admin = new Admin("Cath", "123", "Schneider");
            ResponseEntity<Admin> responseEntity = restTemplate.postForEntity(fooResourceUrl, admin, Admin.class);
            ResponseEntity<Admin> responseEntity1 = restTemplate.postForEntity(fooResourceUrl, admin, Admin.class);
        }
        catch(HttpClientErrorException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("409 null");
        }
    }

}
