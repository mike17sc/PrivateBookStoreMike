package com.training.service;

import com.training.dao.AdminRepository;
import com.training.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by Mschneider on 04-06-17.
 */
@Component
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Collection<Admin> list() {
        Collection<Admin> admins = adminRepository.findAll();
        return admins;
    }

    @Override
    public Admin get(Long id) {
        return adminRepository.findOne(id);
    }

    @Override
    public Admin create(Admin admin) {
        if (admin.getId() != null) {
            return null;
        }
        if(admin.getName().isEmpty()||admin.getPassword().isEmpty()||admin.getUsername().isEmpty()){
            return null;
        }
        else {
            return adminRepository.save(admin);
        }
    }

    @Override
    public Admin update(Admin admin) {
        if (admin.getId() == null) {
            return null;
        } else {
            return adminRepository.save(admin);
        }
    }

    @Override
    public boolean delete(Long id) {
        adminRepository.delete(id);
        return true;
    }
}
