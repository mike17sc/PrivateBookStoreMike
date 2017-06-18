package com.training.controller;

import com.training.model.LoginLog;
import com.training.model.User;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

public class LoginLogRestControllerTest {
    @Test
    public void testCreateViewDeleteUpdate() {
        //added
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/api/loginLog";
        User user=new User("Mikesc","123","Schneider");
        ResponseEntity<User>userResponseEntity=restTemplate.postForEntity("http://localhost:8080/api/user",user,User.class);
        LoginLog loginLog = new LoginLog(userResponseEntity.getBody(),new Date());
        ResponseEntity<LoginLog> loginResponseEntity = restTemplate.postForEntity(fooResourceUrl, loginLog, LoginLog.class);
        Assertions.assertThat(loginResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        System.out.println("loginLog added...");

        //listed
        ResponseEntity<String> response1 = restTemplate.getForEntity(fooResourceUrl, String.class);
        System.out.println(response1.getBody());
        Assertions.assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.OK);

        //updated
        loginResponseEntity.getBody().setLogout(new Date());
        restTemplate.put(fooResourceUrl + "/" + loginResponseEntity.getBody().getId(), loginResponseEntity.getBody(), LoginLog.class);
        System.out.println("LoginLog updated");

        //deleted
        restTemplate.delete(fooResourceUrl + "/" + loginResponseEntity.getBody().getId());
        System.out.println("LoginLog deleted");
    }
}
