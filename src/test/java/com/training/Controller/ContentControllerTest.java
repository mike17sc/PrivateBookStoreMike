package com.training.Controller;

import com.training.model.Content;
import com.training.service.ContentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by Mschneider on 04-06-17.
 */
@RestController
public class ContentControllerTest {
    @Autowired
    private ContentServiceImpl contentServiceImpl;

    @GetMapping("/content")
    public Collection<Content> getContents(){
        return contentServiceImpl.list();
    }
    @GetMapping("/content/{id}")
    public ResponseEntity getContent(@PathVariable("id")Long id){
        Content content= contentServiceImpl.get(id);
        if (content==null){
            return new ResponseEntity("No content found for ID" + id, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity(content,HttpStatus.OK);
        }
    }
    @PostMapping(value = "/content")
    public ResponseEntity createCustomer(@RequestBody Content content) {

        contentServiceImpl.create(content);

        return new ResponseEntity(content, HttpStatus.OK);
    }

    @DeleteMapping("/content/delete/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Long id) {

        if (!contentServiceImpl.delete(id)) {
            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(id, HttpStatus.OK);

    }

    @PutMapping("/content/update/{id}")
    public ResponseEntity updateCustomer(@PathVariable Long id, @RequestBody Content content) {

        content = contentServiceImpl.update(content);

        if (null == content) {
            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(content, HttpStatus.OK);
    }
}
