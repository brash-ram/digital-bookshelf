package com.brash.digital_bookshelf.data.service;

import com.brash.digital_bookshelf.data.entity.Genre;

import java.util.List;

public interface GenreService {
    Genre getById(long id);

    void add(String name);

    List<Genre> getAll();
}
