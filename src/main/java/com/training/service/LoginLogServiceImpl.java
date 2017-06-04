package com.training.service;

import com.training.dao.LoginLogRepository;
import com.training.model.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by Mschneider on 04-06-17.
 */
@Component
public class LoginLogServiceImpl implements LoginLogService {
    @Autowired
    private LoginLogRepository loginLogRepository;

    @Override
    public Collection<LoginLog> list() {
        Collection<LoginLog> loginLogs = loginLogRepository.findAll();
        return loginLogs;
    }

    @Override
    public LoginLog get(Long id) {
        return loginLogRepository.findOne(id);
    }

    @Override
    public LoginLog create(LoginLog loginLog) {
        if (loginLog.getId() != null) {
            return null;
        }
        else {
            return loginLogRepository.save(loginLog);
        }
    }

    @Override
    public LoginLog update(LoginLog loginLog) {
        if (loginLog.getId() == null) {
            return null;
        } else {
            return loginLogRepository.save(loginLog);
        }
    }

    @Override
    public boolean delete(Long id) {
        loginLogRepository.delete(id);
        return true;
    }
}
