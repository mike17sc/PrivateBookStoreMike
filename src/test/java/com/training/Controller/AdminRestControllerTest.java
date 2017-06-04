package com.training.Controller;

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
    public void testCreate() {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/admin";
        Admin admin = new Admin("Mikesc", "123", "Schneider");
        ResponseEntity<Admin> responseEntity = restTemplate.postForEntity(fooResourceUrl, admin, Admin.class);
        System.out.println(responseEntity.getBody());
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        System.out.println("admin added...");
    }
    @Test
    public void testCreateNull() throws Exception{
        try{
            RestTemplate restTemplate = new RestTemplate();
            String fooResourceUrl = "http://localhost:8080/admin";
            Admin admin = new Admin("","","");
            ResponseEntity<Admin> responseEntity = restTemplate.postForEntity(fooResourceUrl, admin, Admin.class);
            System.out.println(responseEntity.getBody());
            Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
            System.out.println("admin added...");
        }
        catch(HttpClientErrorException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("422 null");
        }

    }

}
