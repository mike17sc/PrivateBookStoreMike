package com.training.Controller;

import com.training.model.User;
import com.training.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Mschneider on 04-06-17.
 */
/*@RestController
public class UserControllerTest {
    List<String> listOfString=new ArrayList<>();
    RestTemplate restTemplate=new RestTemplate();
    String fooResourceUrl="http://localhost:8080/user/login";
    HttpEntity<MultiValueMap<String,String>> resust= new HttpEntity<MultiValueMap<String,String>>("Mikesc","123");
    ResponseEntity<String>respnse=restTemplate.postForEntity(fooResourceUrl,);

}*/
