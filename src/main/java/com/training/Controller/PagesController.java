package com.training.Controller;

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

    @GetMapping("/pages")
    public Collection<Pages> getPagess(){
        return pagesServiceImpl.list();
    }
    @GetMapping("/pages/{id}")
    public ResponseEntity getPages(@PathVariable("id")Long id){
        Pages pages= pagesServiceImpl.get(id);
        if (pages==null){
            return new ResponseEntity("No pages found for ID" + id, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity(pages,HttpStatus.OK);
        }
    }
    @PostMapping(value = "/customers")
    public ResponseEntity createCustomer(@RequestBody Pages pages) {

        pagesServiceImpl.create(pages);

        return new ResponseEntity(pages, HttpStatus.OK);
    }

    @DeleteMapping("/customers/delete/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Long id) {

        if (!pagesServiceImpl.delete(id)) {
            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(id, HttpStatus.OK);

    }

    @PutMapping("/customers/update/{id}")
    public ResponseEntity updateCustomer(@PathVariable Long id, @RequestBody Pages pages) {

        pages = pagesServiceImpl.update(pages);

        if (null == pages) {
            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(pages, HttpStatus.OK);
    }
}
