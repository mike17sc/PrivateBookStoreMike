package com.training.controller;

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
public class ContentController {
    @Autowired
    private ContentServiceImpl contentServiceImpl;

    @GetMapping("api/content")
    public Collection<Content> getContents(){
        return contentServiceImpl.list();
    }
    @GetMapping("api/content/{id}")
    public ResponseEntity getContent(@PathVariable("id")Long id){
        Content content= contentServiceImpl.get(id);
        if (content==null){
            return new ResponseEntity("No content found for ID" + id, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity(content,HttpStatus.OK);
        }
    }
    @PostMapping(value = "api/content")
    public ResponseEntity createContent(@RequestBody Content content) {

        contentServiceImpl.create(content);

        return new ResponseEntity(content, HttpStatus.OK);
    }

    @DeleteMapping("api/content/delete/{id}")
    public ResponseEntity deleteContent(@PathVariable Long id) {

        if (!contentServiceImpl.delete(id)) {
            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(id, HttpStatus.OK);

    }

    @PutMapping("api/content/update/{id}")
    public ResponseEntity updateContent(@PathVariable Long id, @RequestBody Content content) {

        content = contentServiceImpl.update(content);

        if (null == content) {
            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(content, HttpStatus.OK);
    }
}
