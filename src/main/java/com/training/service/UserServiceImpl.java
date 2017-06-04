package com.training.service;

import com.training.dao.PagesRepository;
import com.training.dao.UserRepository;
import com.training.model.Pages;
import com.training.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * Created by Mschneider on 04-06-17.
 */
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public Collection<User> list() {
        Collection<User>user= userRepository.findAll();
        return user;
    }

    @Override
    public User get(Long id) {

        return userRepository.findOne(id);
    }

    @Override
    public User create(User user) {
        if(user.getId()!=null){
            return null;
        }
        else{
            return userRepository.save(user);
        }
    }

    @Override
    public User update(User user) {
        if(user.getId()==null){
            return null;
        }
        else {
            return userRepository.save(user);
        }
    }

    @Override
    public boolean delete(Long id) {
        userRepository.delete(id);
        return true;
    }
}
