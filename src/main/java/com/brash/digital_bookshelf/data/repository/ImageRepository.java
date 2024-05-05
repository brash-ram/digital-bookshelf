package com.brash.digital_bookshelf.data.repository;

import com.brash.digital_bookshelf.data.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}