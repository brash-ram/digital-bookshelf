package com.brash.digital_bookshelf.data.repository;

import com.brash.digital_bookshelf.data.entity.AuthorityRole;
import com.brash.digital_bookshelf.data.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRoleRepository extends JpaRepository<AuthorityRole, Long> {

    boolean existsByName(Role name);

    AuthorityRole getAuthorityRoleByName(Role name);
}