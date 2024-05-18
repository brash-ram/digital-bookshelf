package com.brash.digital_bookshelf.data.repository;

import com.brash.digital_bookshelf.data.entity.BookSeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookSeriesRepository extends JpaRepository<BookSeries, Long> {

    List<BookSeries> findAllByAuthorId(long id);
}