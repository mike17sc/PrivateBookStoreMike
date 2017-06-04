package com.training.service;

import com.training.model.Client;

import java.util.Collection;

/**
 * Created by Mschneider on 04-06-17.
 */
public interface ClientService {
    Collection<Client> list();

    Client get(Long id);

    Client create(Client client);

    Client update(Client client);

    boolean delete(Long id);
}
