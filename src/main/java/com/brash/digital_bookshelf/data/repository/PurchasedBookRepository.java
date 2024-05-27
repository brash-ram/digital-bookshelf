package com.brash.digital_bookshelf.data.repository;

import com.brash.digital_bookshelf.data.entity.PurchasedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchasedBookRepository extends JpaRepository<PurchasedBook, Long> {

    List<PurchasedBook> findAllByUserId(long userId);
}