package com.training.dao;

import com.training.model.Pages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Mschneider on 04-06-17.
 */
@Repository
public interface PagesRepository extends JpaRepository<Pages, Long> {
    @Query("SELECT p,b.quantity FROM Pages p,BuyBook b WHERE p.book.id=b.book.id")
    public List<Object[]> totalSoldPages();
}
