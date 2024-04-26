package com.brash.digital_bookshelf.data.service;

import com.brash.digital_bookshelf.data.entity.User;

public interface UserService {
    User getByUsername(String username);
    User getById(long id);
}
