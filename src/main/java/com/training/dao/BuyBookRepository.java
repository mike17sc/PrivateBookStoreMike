package com.training.dao;

import com.training.model.Book;
import com.training.model.BuyBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by Mschneider on 04-06-17.
 */
@Repository
public interface BuyBookRepository extends JpaRepository<BuyBook, Long> {
    @Query("SELECT SUM(b.quantity)FROM BuyBook b")
    public int totalBookSold();
    @Query(" SELECT b.book FROM BuyBook b GROUP BY b.book ORDER BY SUM(b.quantity) DESC")
    public List<Book> bestSeller();
}
