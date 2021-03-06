package com.training.controller;

import com.training.model.Pages;
import com.training.service.PagesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by Mschneider on 04-06-17.
 */
@RestController
public class PagesController {
    @Autowired
    private PagesServiceImpl pagesServiceImpl;

    @GetMapping("api/pages")
    public Collection<Pages> getPagess(){
        return pagesServiceImpl.list();
    }
    @GetMapping("api/pages/{id}")
    public ResponseEntity getPages(@PathVariable("id")Long id){
        Pages pages= pagesServiceImpl.get(id);
        if (pages==null){
            return new ResponseEntity("No pages found for ID" + id, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity(pages,HttpStatus.OK);
        }
    }
    @PostMapping(value = "api/pages")
    public ResponseEntity createPages(@RequestBody Pages pages) {

        pagesServiceImpl.create(pages);

        return new ResponseEntity(pages, HttpStatus.CREATED);
    }

    @DeleteMapping("api/pages/{id}")
    public ResponseEntity deletePages(@PathVariable Long id) {

        if (!pagesServiceImpl.delete(id)) {
            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(id, HttpStatus.OK);

    }

    @PutMapping("api/pages/{id}")
    public ResponseEntity updatePages(@PathVariable Long id, @RequestBody Pages pages) {

        pages = pagesServiceImpl.update(pages);

        if (null == pages) {
            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(pages, HttpStatus.OK);
    }
}
