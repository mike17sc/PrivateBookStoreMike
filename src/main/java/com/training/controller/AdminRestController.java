package com.training.controller;

import com.training.model.Admin;
import com.training.service.AdminServiceImpl;
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
public class AdminRestController {
    @Autowired
    private AdminServiceImpl adminServiceImpl;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("api/admin")
    public Collection<Admin> getAdmins(){
        return adminServiceImpl.list();
    }
    @GetMapping("api/admin/{id}")
    public ResponseEntity getAdmin(@PathVariable("id")Long id){
        Admin admin= adminServiceImpl.get(id);
        if (admin==null){
            return new ResponseEntity("No admin found for ID" + id, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity(admin,HttpStatus.OK);
        }
    }
    @PostMapping(value = "api/admin")
    public ResponseEntity createAdmin(@RequestBody Admin admin) {
        if(admin==null){
            return new ResponseEntity(admin,HttpStatus.UNPROCESSABLE_ENTITY);
        }
        else if(userServiceImpl.get(admin.getUsername())!=null){
            return new ResponseEntity(admin, HttpStatus.CONFLICT);
        }
        else{
            adminServiceImpl.create(admin);
            return new ResponseEntity(admin, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("api/admin/delete/{id}")
    public ResponseEntity deleteAdmin(@PathVariable Long id) {

        if (!adminServiceImpl.delete(id)) {
            return new ResponseEntity("No Admin found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(id, HttpStatus.OK);

    }

    @PutMapping("api/admin/update/{id}")
    public ResponseEntity updateAdmin(@PathVariable Long id, @RequestBody Admin admin) {

        admin = adminServiceImpl.update(admin);

        if (null == admin) {
            return new ResponseEntity("No Admin found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(admin, HttpStatus.OK);
    }
}
