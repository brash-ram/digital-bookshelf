package com.brash.digital_bookshelf.data.service;

import com.brash.digital_bookshelf.data.entity.Book;

import java.util.List;

public interface BookService {
    Book getById(long id);

    List<Book> getBookNames();

    List<Book> getLastBooks();

    List<Book> getHomeLastBooks();

    List<Book> search(String name);
}
