package com.brash.digital_bookshelf.data.repository;

import com.brash.digital_bookshelf.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsByName(String name);
    boolean existsByUsername(String name);
}