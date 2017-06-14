package com.training.controller;

import com.training.model.Admin;
import com.training.model.User;
import com.training.service.AdminService;
import com.training.service.AdminServiceImpl;
import com.training.service.UserServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by Mschneider on 04-06-17.
 */

public class UserControllerTest {
    @Autowired
    private AdminServiceImpl adminServiceImp;
    @Autowired
    private UserServiceImpl userService;
    @Test
    public void testCreateViewDeleteUpdate() {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/api/user";
        User user = new User("Mikesch", "123", "Schneider");
        ResponseEntity<User> responseEntity = restTemplate.postForEntity(fooResourceUrl, user, User.class);
        System.out.println(responseEntity.getBody().getId()+responseEntity.getBody().getUsername()+responseEntity.getBody().getName());
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        System.out.println("user added...");
        ResponseEntity<String> responseEntity1= restTemplate.getForEntity(fooResourceUrl,String.class);
        /*fooResourceUrl="api/user/update/1";
        User user1 = new User("Mikesch", "123", "Schneider");
        ResponseEntity<User> responseEntity2 = restTemplate.put(fooResourceUrl,user1,);
        Assertions.assertThat(responseEntity2.getStatusCode()).isEqualTo(HttpStatus.OK);*/
    }
    @Test
    public void testCreateNull() throws Exception {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String fooResourceUrl = "http://localhost:8080/api/user";
            User user = new User("", "", "");
            ResponseEntity<User> responseEntity = restTemplate.postForEntity(fooResourceUrl, user, User.class);
            System.out.println(responseEntity.getBody());
            Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
            System.out.println("user added...");
        } catch (HttpClientErrorException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("422 null");
        }

    }
    @Test
    public void testLogin()throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/api/user/login/Mikesc/123";
        Admin admin = new Admin("Mikeschn", "123", "Schneider");
        ResponseEntity<Admin> responseEntity = restTemplate.postForEntity("http://localhost:8080/api/admin", admin, Admin.class);
        ResponseEntity<List> responseEntity1 = restTemplate.getForEntity(fooResourceUrl,List.class);
        Assertions.assertThat(responseEntity1.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    public void testLogout()throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        Admin tempAdmin= new Admin("Mikeschne", "123", "Schneider");
        User user=userService.get("Mikeschne");
        Long adminId=user.getId();
        String fooResourceUrl = "http://localhost:8080/api/user/logout/"+adminId;
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(fooResourceUrl,List.class);
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
