package com.brash.digital_bookshelf.data.service.impl;

import com.brash.digital_bookshelf.data.entity.BookSeries;
import com.brash.digital_bookshelf.data.entity.User;
import com.brash.digital_bookshelf.data.repository.BookSeriesRepository;
import com.brash.digital_bookshelf.data.service.BookSeriesService;
import com.brash.digital_bookshelf.data.service.UserService;
import com.brash.digital_bookshelf.exception.ResourceNotFoundException;
import com.brash.digital_bookshelf.utils.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookSeriesServiceImpl implements BookSeriesService {

    private final BookSeriesRepository bookSeriesRepository;

    private final AuthUtils authUtils;

    private final UserService userService;

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

    @Transactional
    @Override
    public void update(long id, String name, String description) {
        BookSeries series = this.getById(id);
        series.setName(name).setDescription(description);
        bookSeriesRepository.save(series);
    }

    @Override
    public void delete(long id) {
        bookSeriesRepository.deleteById(id);
    }

    @Transactional
    @Override
    public List<BookSeries> mySeries() {
        User user = userService.getById(authUtils.getUserEntity().getId());
        return bookSeriesRepository.findAllByAuthorId(user.getAuthorInfo().getId());
    }

    @Override
    public List<BookSeries> authorSeries(long authorId) {
        return bookSeriesRepository.findAllByAuthorId(authorId);
    }
}
