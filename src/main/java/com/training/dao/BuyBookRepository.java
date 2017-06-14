package com.training.dao;

import com.training.model.BuyBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Mschneider on 04-06-17.
 */
@Repository
public interface BuyBookRepository extends JpaRepository<BuyBook, Long> {
    @Query("SELECT SUM(b.quantity)FROM BuyBook b")
    public int totalBookSold();
    @Query("SELECT b.Book,SUM(b.quantity)FROM BuyBook b GROUP BY b.book ORDER BY b.quantity ASC")
    public int topFive();
}
