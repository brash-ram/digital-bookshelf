package com.brash.digital_bookshelf.data.repository;

import com.brash.digital_bookshelf.data.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    void deleteByName(String name);

    boolean existsByName(String name);

    Optional<Genre> findByName(String name);
}