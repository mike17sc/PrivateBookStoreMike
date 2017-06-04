package com.training.service;

import com.training.dao.ClientRepository;
import com.training.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by Mschneider on 04-06-17.
 */
@Component
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Collection<Client> list() {
        Collection<Client> client = clientRepository.findAll();
        return client;
    }

    @Override
    public Client get(Long id) {
        return clientRepository.findOne(id);
    }

    @Override
    public Client create(Client client) {
        if (client.getId() != null) {
            return null;
        } else {
            return clientRepository.save(client);
        }
    }

    @Override
    public Client update(Client client) {
        if (client.getId() == null) {
            return null;
        } else {
            return clientRepository.save(client);
        }
    }

    @Override
    public boolean delete(Long id) {
        clientRepository.delete(id);
        return true;
    }
}
