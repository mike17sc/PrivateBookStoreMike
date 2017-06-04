package com.training.Controller;

import com.training.model.User;
import com.training.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by Mschneider on 04-06-17.
 */
@RestController
public class UserControllerTest {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/user")
    public Collection<User> getUsers(){
        return userServiceImpl.list();
    }
    @GetMapping("/user/{id}")
    public ResponseEntity getUser(@PathVariable("id")Long id){
        User user= userServiceImpl.get(id);
        if (user==null){
            return new ResponseEntity("No user found for ID" + id, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity(user,HttpStatus.OK);
        }
    }
    @PostMapping(value = "/user")
    public ResponseEntity createCustomer(@RequestBody User user) {

        userServiceImpl.create(user);

        return new ResponseEntity(user, HttpStatus.OK);
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Long id) {

        if (!userServiceImpl.delete(id)) {
            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(id, HttpStatus.OK);

    }

    @PutMapping("/user/update/{id}")
    public ResponseEntity updateCustomer(@PathVariable Long id, @RequestBody User user) {

        user = userServiceImpl.update(user);

        if (null == user) {
            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(user, HttpStatus.OK);
    }
}
