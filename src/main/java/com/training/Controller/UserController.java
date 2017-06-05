package com.training.Controller;

import com.training.model.Admin;
import com.training.model.Client;
import com.training.model.LoginLog;
import com.training.model.User;
import com.training.service.AdminService;
import com.training.service.ClientService;
import com.training.service.LoginLogServiceImpl;
import com.training.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;

/**
 * Created by Mschneider on 04-06-17.
 */
@RestController
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    public AdminService adminService;
    @Autowired
    public ClientService clientService;
    @Autowired
    public LoginLogServiceImpl loginLogService;

    @GetMapping("/user")
    public Collection<User> getUsers() {
        return userServiceImpl.list();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity getUser(@PathVariable("id") Long id) {
        User user = userServiceImpl.get(id);
        if (user == null) {
            return new ResponseEntity("No user found for ID" + id, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(user, HttpStatus.OK);
        }
    }

    @PostMapping("/user/login")
    public ResponseEntity loginUser(@RequestBody String username, String password) {
        User user = userServiceImpl.get(username, password);

        if (user == null) {
            return new ResponseEntity("Wrong username or password", HttpStatus.UNAUTHORIZED);
        } else {
            LoginLog log = new LoginLog(user, new Date(), null);
            loginLogService.create(log);
            Admin admin = adminService.get(user.getId());
            if (admin != null) {
                return new ResponseEntity(admin, HttpStatus.OK);
            } else {

                Client client = clientService.get(user.getId());
                return new ResponseEntity(client, HttpStatus.OK);
            }

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
