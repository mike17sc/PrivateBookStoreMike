package com.training.controller;

import com.training.model.LoginLog;
import com.training.service.LoginLogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by Mschneider on 04-06-17.
 */
@RestController
public class LoginLogRestController {
    @Autowired
    private LoginLogServiceImpl loginLogServiceImpl;

    @GetMapping("api/loginLog")
    public Collection<LoginLog> getLoginLogs(){
        return loginLogServiceImpl.list();
    }
    @GetMapping("api/loginLog/{id}")
    public ResponseEntity getLoginLog(@PathVariable("id")Long id){
        LoginLog loginLog= loginLogServiceImpl.get(id);
        if (loginLog==null){
            return new ResponseEntity("No loginLog found for ID" + id, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity(loginLog,HttpStatus.OK);
        }
    }
    @PostMapping(value = "api/loginLog")
    public ResponseEntity createLoginLog(@RequestBody LoginLog loginLog) {
        if(loginLogServiceImpl.create(loginLog)==null){
            return new ResponseEntity(loginLog,HttpStatus.UNPROCESSABLE_ENTITY);
        }
        else{
            loginLogServiceImpl.create(loginLog);
            return new ResponseEntity(loginLog, HttpStatus.CREATED);
        }

    }

    @DeleteMapping("api/loginLog/delete/{id}")
    public ResponseEntity deleteLoginLog(@PathVariable Long id) {

        if (!loginLogServiceImpl.delete(id)) {
            return new ResponseEntity("No LoginLog found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(id, HttpStatus.OK);

    }

    @PutMapping("api/loginLog/update/{id}")
    public ResponseEntity updateLoginLog(@PathVariable Long id, @RequestBody LoginLog loginLog) {

        loginLog = loginLogServiceImpl.update(loginLog);

        if (null == loginLog) {
            return new ResponseEntity("No LoginLog found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(loginLog, HttpStatus.OK);
    }
}
