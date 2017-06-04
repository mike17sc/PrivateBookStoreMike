package com.training.service;

import com.training.model.Admin;
import sun.text.resources.zh.CollationData_zh;

import java.util.Collection;

/**
 * Created by Mschneider on 04-06-17.
 */
public interface AdminService {
    Collection<Admin>list();
    Admin get(Long id);
    Admin create(Admin admin);
    Admin update(Admin admin);
    boolean delete(Long id);
}
