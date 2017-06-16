package com.training.controller;

import com.training.model.LoginLog;
import com.training.model.User;
import com.training.model.User;
import com.training.service.UserService;
import com.training.service.UserServiceImpl;
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
    private UserServiceImpl userServiceImp;
    @Autowired
    private UserServiceImpl userService;
    @Test
    public void testCreateViewDeleteUpdate() {
        //added
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/api/user";
        User user = new User("Mikesc", "123", "Schneider");
        ResponseEntity<User> responseEntity = restTemplate.postForEntity(fooResourceUrl, user, User.class);
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        System.out.println("user added...");

        //listed
        ResponseEntity<String> response1 = restTemplate.getForEntity(fooResourceUrl, String.class);
        System.out.println(response1.getBody());
        Assertions.assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.OK);

        //updated
        responseEntity.getBody().setName("moidhjsfomqdhfsqfdsh");
        restTemplate.put(fooResourceUrl + "/" + responseEntity.getBody().getId(), responseEntity.getBody(), User.class);
        System.out.println("User updated");

        //deleted
        restTemplate.delete(fooResourceUrl + "/" + responseEntity.getBody().getId());
        System.out.println("User deleted");
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
            Assertions.assertThat(e.getMessage()).isEqualTo("409 null");
        }

    }
    @Test
    public void testLogin()throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/api/user/login/Mikeschn/123";
        User user = new User("Mikeschn", "123", "Schneider");
        ResponseEntity<User> responseEntity = restTemplate.postForEntity("http://localhost:8080/api/user", user, User.class);
        ResponseEntity<LoginLog> responseEntity1 = restTemplate.getForEntity(fooResourceUrl,LoginLog.class);
        Assertions.assertThat(responseEntity1.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    public void testLogout()throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        User tempUser= new User("Mikeschne", "123", "Schneider");
        User user=userService.get("Mikeschne");
        Long userId=user.getId();
        String fooResourceUrl = "http://localhost:8080/api/user/logout/"+userId;
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(fooResourceUrl,List.class);
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
