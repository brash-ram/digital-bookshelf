package com.brash.digital_bookshelf.data.service;

import com.brash.digital_bookshelf.data.entity.BookSeries;

import java.util.List;

public interface BookSeriesService {

    BookSeries getById(long id);

    void add(String name, String description);

    void update(long id, String name, String description);

    void delete(long id);

    List<BookSeries> mySeries();

    List<BookSeries> authorSeries(long authorId);
}
