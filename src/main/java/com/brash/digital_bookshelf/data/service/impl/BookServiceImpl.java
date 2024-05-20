package com.brash.digital_bookshelf.data.service.impl;

import com.brash.digital_bookshelf.data.entity.Book;
import com.brash.digital_bookshelf.data.repository.BookRepository;
import com.brash.digital_bookshelf.data.service.BookService;
import com.brash.digital_bookshelf.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Book getById(long id) {
        return bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format("Book with id: %s -- is not found", id)
                        )
                );
    }
}
