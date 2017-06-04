package com.training.service;

import com.training.dao.AdminRepository;
import com.training.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * Created by Mschneider on 04-06-17.
 */
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Override
    public Collection<Admin> list() {
        Collection<Admin>admins=adminRepository.findAll();
        return admins;
    }

    @Override
    public Admin get(Long id) {
        return adminRepository.findOne(id);
    }

    @Override
    public Admin create(Admin admin) {
        if(admin.getId()!=null){
            return null;
        }
        else{
            return adminRepository.save(admin);
        }
    }

    @Override
    public Admin update(Admin admin) {
        if(admin.getId()==null){
            return null;
        }
        else {
            return adminRepository.save(admin);
        }
    }

    @Override
    public boolean delete(Long id) {
        adminRepository.delete(id);
        return true;
    }
}
