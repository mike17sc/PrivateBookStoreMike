package com.training.controller;

import com.training.model.Client;
import com.training.service.ClientServiceImpl;
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
public class ClientController {
    @Autowired
    private ClientServiceImpl clientServiceImpl;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("api/client")
    public Collection<Client> getClients() {
        return clientServiceImpl.list();
    }

    @GetMapping("api/client/{id}")
    public ResponseEntity getClient(@PathVariable("id") Long id) {
        Client client = clientServiceImpl.get(id);
        if (client == null) {
            return new ResponseEntity("No client found for ID" + id, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(client, HttpStatus.OK);
        }
    }

    @PostMapping(value = "api/client")
    public ResponseEntity createClient(@RequestBody Client client) {
        if (client == null) {
            return new ResponseEntity(client, HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (userServiceImpl.get(client.getUsername()) != null) {
            return new ResponseEntity(client, HttpStatus.CONFLICT);
        } else {
            if(clientServiceImpl.create(client)==null){
                return new ResponseEntity(client, HttpStatus.CONFLICT);
            }
            else{
                return new ResponseEntity(client, HttpStatus.CREATED);
            }
        }

    }

    @DeleteMapping("api/client/delete/{id}")
    public ResponseEntity deleteClient(@PathVariable Long id) {

        if (!clientServiceImpl.delete(id)) {
            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(id, HttpStatus.OK);

    }

    @PutMapping("api/client/update/{id}")
    public ResponseEntity updateClient(@PathVariable Long id, @RequestBody Client client) {

        client = clientServiceImpl.update(client);

        if (null == client) {
            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(client, HttpStatus.OK);
    }
}
