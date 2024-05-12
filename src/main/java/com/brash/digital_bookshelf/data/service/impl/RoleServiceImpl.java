package com.brash.digital_bookshelf.data.service.impl;

import com.brash.digital_bookshelf.data.entity.AuthorityRole;
import com.brash.digital_bookshelf.data.entity.User;
import com.brash.digital_bookshelf.data.enums.Role;
import com.brash.digital_bookshelf.data.repository.AuthorityRoleRepository;
import com.brash.digital_bookshelf.data.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final AuthorityRoleRepository authorityRoleRepository;

    @Override
    public User granted(User user, Role role) {
        AuthorityRole authorityRole = authorityRoleRepository.getAuthorityRoleByName(role);
        user.getRoles().add(authorityRole);
        return user;
    }
}
