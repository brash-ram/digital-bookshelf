package com.brash.digital_bookshelf.data.repository;

import com.brash.digital_bookshelf.data.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
}