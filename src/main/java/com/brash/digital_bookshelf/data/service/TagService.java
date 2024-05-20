package com.brash.digital_bookshelf.data.service;

import com.brash.digital_bookshelf.data.entity.Genre;
import com.brash.digital_bookshelf.data.entity.Tag;

import java.util.List;

public interface TagService {
    Tag getById(long id);

    List<Tag> getAll();

    List<Tag> getAllByNames(List<String> names);
}
