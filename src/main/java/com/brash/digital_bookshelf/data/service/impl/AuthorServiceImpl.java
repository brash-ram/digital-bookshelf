package com.brash.digital_bookshelf.data.service.impl;

import com.brash.digital_bookshelf.data.entity.AuthorInfo;
import com.brash.digital_bookshelf.data.repository.AuthorInfoRepository;
import com.brash.digital_bookshelf.data.service.AuthorService;
import com.brash.digital_bookshelf.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorInfoRepository authorInfoRepository;

    @Override
    public AuthorInfo getById(long id) {
        return authorInfoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format("Author with id: %s -- is not found", id)
                        )
                );
    }

    @Override
    public AuthorInfo getByUserId(long userId) {
        return authorInfoRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format("Author with user id: %s -- is not found", userId)
                        )
                );
    }
}
