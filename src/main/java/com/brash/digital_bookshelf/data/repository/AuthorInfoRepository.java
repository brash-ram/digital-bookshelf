package com.brash.digital_bookshelf.data.repository;

import com.brash.digital_bookshelf.data.entity.AuthorInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorInfoRepository extends JpaRepository<AuthorInfo, Long> {

    Optional<AuthorInfo> findByUserId(long id);
}