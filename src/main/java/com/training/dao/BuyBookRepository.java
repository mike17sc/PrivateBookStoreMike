package com.training.dao;

import com.training.model.Admin;
import com.training.model.BuyBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Mschneider on 04-06-17.
 */
@Repository
public interface BuyBookRepository extends JpaRepository<BuyBook,Long> {
}
