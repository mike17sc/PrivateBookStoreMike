package com.training.controller;

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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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

    @GetMapping("/api/user")
    public Collection<User> getUsers() {
        return userServiceImpl.list();
    }

    @GetMapping("api/user/{id}")
    public ResponseEntity getUser(@PathVariable("id") Long id) {
        User user = userServiceImpl.get(id);
        if (user == null) {
            return new ResponseEntity("No user found for ID" + id, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(user, HttpStatus.OK);
        }
    }

    @GetMapping("api/user/login/{username}/{password}")
    public ResponseEntity loginUser(@PathVariable("username") String username, @PathVariable("password") String password) {
        User user = userServiceImpl.get(username, password);

        if (user == null) {
            return new ResponseEntity("Wrong username or password", HttpStatus.UNAUTHORIZED);
        } else {
            LoginLog log = new LoginLog(user, new Date());
            loginLogService.create(log);
            return new ResponseEntity(log, HttpStatus.OK);
        }

    }

    @GetMapping("api/user/logout/{logId}")
    public ResponseEntity logoutUser(@PathVariable("logId") Long logId) {
        LoginLog log = loginLogService.get(logId);
        log.setLogout(new Date());
        log.setDuration(Math.abs(log.getLogon().getTime() - log.getLogout().getTime()));
        loginLogService.update(log);
        return new ResponseEntity(log, HttpStatus.OK);
    }

    @PostMapping(value = "api/user")
    public ResponseEntity createUser(@RequestBody User user) {
        if (user == null) {
            return new ResponseEntity(user, HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (userServiceImpl.get(user.getUsername()) != null) {
            return new ResponseEntity(user, HttpStatus.CONFLICT);
        } else {
            if (userServiceImpl.create(user) == null) {
                return new ResponseEntity(user, HttpStatus.CONFLICT);
            } else {
                return new ResponseEntity(user, HttpStatus.CREATED);
            }
        }

    }

    @DeleteMapping("api/user/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {

        if (!userServiceImpl.delete(id)) {
            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(id, HttpStatus.OK);

    }

    @PutMapping("api/user/{id}")
    public ResponseEntity updateCustomer(@PathVariable Long id, @RequestBody User user) {

        user = userServiceImpl.update(user);

        if (null == user) {
            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(user, HttpStatus.OK);
    }
}
