package com.brash.digital_bookshelf.data.service;

import com.brash.digital_bookshelf.data.entity.AuthorInfo;
import com.brash.digital_bookshelf.data.entity.Image;

public interface AuthorService {
    AuthorInfo getById(long id);
    AuthorInfo getByUserId(long userId);
}
