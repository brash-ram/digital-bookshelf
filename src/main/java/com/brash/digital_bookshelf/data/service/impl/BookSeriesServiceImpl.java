package com.brash.digital_bookshelf.data.service.impl;

import com.brash.digital_bookshelf.data.entity.BookSeries;
import com.brash.digital_bookshelf.data.repository.BookSeriesRepository;
import com.brash.digital_bookshelf.data.service.BookSeriesService;
import com.brash.digital_bookshelf.exception.ResourceNotFoundException;
import com.brash.digital_bookshelf.utils.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookSeriesServiceImpl implements BookSeriesService {

    private final BookSeriesRepository bookSeriesRepository;

    private final AuthUtils authUtils;

    @Override
    public BookSeries getById(long id) {
        return bookSeriesRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format("Book series with id: %s -- is not found", id)
                        )
                );
    }

    @Override
    public void add(String name, String description) {
        bookSeriesRepository.save(
                new BookSeries()
                        .setName(name)
                        .setDescription(description)
                        .setAuthor(authUtils.getUserEntity().getAuthorInfo())
        );
    }

    @Override
    public void delete(long id) {
        bookSeriesRepository.deleteById(id);
    }

    @Override
    public List<BookSeries> mySeries() {
        return bookSeriesRepository.findAllByAuthorId(authUtils.getUserEntity().getAuthorInfo().getId());
    }

    @Override
    public List<BookSeries> authorSeries(long authorId) {
        return bookSeriesRepository.findAllByAuthorId(authorId);
    }
}
