package com.training.controller;

import com.training.model.Client;
import com.training.model.Client;
import com.training.service.ClientServiceImpl;
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

public class ClientControllerTest {
    @Test
    public void testCreateViewDeleteUpdate() {
        //added
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/api/client";
        Client client = new Client("Mikesc", "123", "Schneider","Jolimont","mike17sc@hotmail");
        ResponseEntity<Client> responseEntity = restTemplate.postForEntity(fooResourceUrl, client, Client.class);
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        System.out.println("client added...");

        //listed
        ResponseEntity<String>response1 = restTemplate.getForEntity(fooResourceUrl, String.class);
        System.out.println(response1.getBody());
        Assertions.assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.OK);

        //updated
        responseEntity.getBody().setName("moidhjsfomqdhfsqfdsh");
        restTemplate.put(fooResourceUrl+"/"+responseEntity.getBody().getId(),responseEntity.getBody(),Client.class);
        System.out.println("Client updated");

        //deleted
        restTemplate.delete(fooResourceUrl+"/"+responseEntity.getBody().getId());
        System.out.println("User deleted");
    }
    @Test
    public void testCreateNull() throws Exception {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String fooResourceUrl = "http://localhost:8080/api/client";
            Client client = new Client("", "", "","","");
            ResponseEntity<Client> responseEntity = restTemplate.postForEntity(fooResourceUrl, client, Client.class);
            Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (HttpClientErrorException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("409 null");
        }

    }
    @Test
    public void testDouble() throws Exception {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String fooResourceUrl = "http://localhost:8080/api/client";
            Client client = new Client("Cath", "123", "Schneider","jolimont","cath@moi.com");
            restTemplate.postForEntity(fooResourceUrl, client, Client.class);
            restTemplate.postForEntity(fooResourceUrl, client, Client.class);
        }
        catch(HttpClientErrorException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("409 null");
        }
    }
}
