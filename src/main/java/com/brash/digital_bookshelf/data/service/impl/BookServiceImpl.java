package com.brash.digital_bookshelf.data.service.impl;

import com.brash.digital_bookshelf.data.entity.Book;
import com.brash.digital_bookshelf.data.repository.BookRepository;
import com.brash.digital_bookshelf.data.service.BookService;
import com.brash.digital_bookshelf.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Book> getBookNames() {
        return bookRepository.findAll().stream().toList();
    }

    @Override
    public List<Book> getLastBooks() {
        return bookRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    @Override
    public List<Book> getHomeLastBooks() {
        List<Book> books = getLastBooks();
        if (books.size() > 5) {
            return books.subList(0, 5);
        }
        return  books;
    }

    @Override
    public List<Book> search(String name) {
        return bookRepository.findAllByNameContainingIgnoreCase(name);
    }
}
