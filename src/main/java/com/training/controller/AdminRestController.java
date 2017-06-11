package com.training.controller;

import com.training.model.Admin;
import com.training.service.AdminServiceImpl;
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

    @GetMapping("/admin")
    public Collection<Admin> getAdmins(){
        return adminServiceImpl.list();
    }
    @GetMapping("/admin/{id}")
    public ResponseEntity getAdmin(@PathVariable("id")Long id){
        Admin admin= adminServiceImpl.get(id);
        if (admin==null){
            return new ResponseEntity("No admin found for ID" + id, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity(admin,HttpStatus.OK);
        }
    }
    @PostMapping(value = "/admin")
    public ResponseEntity createAdmin(@RequestBody Admin admin) {
        if(adminServiceImpl.create(admin)==null){
            return new ResponseEntity(admin,HttpStatus.UNPROCESSABLE_ENTITY);
        }
        else{
            adminServiceImpl.create(admin);
            return new ResponseEntity(admin, HttpStatus.CREATED);
        }

    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity deleteAdmin(@PathVariable Long id) {

        if (!adminServiceImpl.delete(id)) {
            return new ResponseEntity("No Admin found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(id, HttpStatus.OK);

    }

    @PutMapping("/admin/update/{id}")
    public ResponseEntity updateAdmin(@PathVariable Long id, @RequestBody Admin admin) {

        admin = adminServiceImpl.update(admin);

        if (null == admin) {
            return new ResponseEntity("No Admin found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(admin, HttpStatus.OK);
    }
}
