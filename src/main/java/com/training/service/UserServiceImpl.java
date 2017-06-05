package com.training.service;

import com.training.dao.UserRepository;
import com.training.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by Mschneider on 04-06-17.
 */
@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Collection<User> list() {
        Collection<User> user = userRepository.findAll();
        return user;
    }

    @Override
    public User get(Long id) {

        return userRepository.findOne(id);
    }
    @Override
    public User get(String username,String password){
        return userRepository.findByUsernameAndPassword(username,password);
    }


    @Override
    public User create(User user) {
        if (user.getId() != null) {
            return null;
        } else {
            return userRepository.save(user);
        }
    }

    @Override
    public User update(User user) {
        if (user.getId() == null) {
            return null;
        } else {
            return userRepository.save(user);
        }
    }

    @Override
    public boolean delete(Long id) {
        userRepository.delete(id);
        return true;
    }
}
