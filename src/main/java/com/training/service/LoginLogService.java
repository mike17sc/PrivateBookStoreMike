package com.training.service;

import com.training.model.LoginLog;

import java.util.Collection;
import java.util.List;

/**
 * Created by Mschneider on 04-06-17.
 */
public interface LoginLogService {
    Collection<LoginLog> list();

    LoginLog get(Long id);

    LoginLog create(LoginLog loginLog);

    LoginLog update(LoginLog loginLog);

    boolean delete(Long id);
    List<Object[]> auditLog();
}
