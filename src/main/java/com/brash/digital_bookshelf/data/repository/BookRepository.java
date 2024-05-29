package com.brash.digital_bookshelf.data.repository;

import com.brash.digital_bookshelf.data.entity.Book;
import com.brash.digital_bookshelf.data.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByGenresContains(Genre genre);

    List<Book> findAllByNameContainingIgnoreCase(String name);
}