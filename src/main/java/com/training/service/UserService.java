package com.training.service;

import com.training.model.User;

import java.util.Collection;

/**
 * Created by Mschneider on 04-06-17.
 */
public interface UserService {
    Collection<User> list();

    User get(Long id);

    User get(String username,String password);

    User create(User user);

    User update(User user);

    boolean delete(Long id);
}
