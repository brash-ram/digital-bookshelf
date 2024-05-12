package com.brash.digital_bookshelf.data.repository;

import com.brash.digital_bookshelf.data.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}