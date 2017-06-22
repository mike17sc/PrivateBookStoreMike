package com.training.dao;

import com.training.model.Book;
import com.training.model.LoginLog;
import com.training.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

/**
 * Created by Mschneider on 04-06-17.
 */
@Repository
public interface LoginLogRepository extends JpaRepository<LoginLog, Long> {
    @Query(" SELECT l.user,SUM(l.duration) FROM LoginLog l GROUP BY l.user ORDER BY l.user DESC")
    public List<Object[]> auditLog();
}
