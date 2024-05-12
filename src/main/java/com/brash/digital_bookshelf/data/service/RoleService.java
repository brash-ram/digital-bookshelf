package com.brash.digital_bookshelf.data.service;

import com.brash.digital_bookshelf.data.entity.User;
import com.brash.digital_bookshelf.data.enums.Role;

public interface RoleService {
    User granted(User user, Role role);
}
