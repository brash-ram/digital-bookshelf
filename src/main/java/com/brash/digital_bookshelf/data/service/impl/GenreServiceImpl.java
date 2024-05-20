package com.brash.digital_bookshelf.data.service.impl;

import com.brash.digital_bookshelf.data.entity.Genre;
import com.brash.digital_bookshelf.data.repository.GenreRepository;
import com.brash.digital_bookshelf.data.service.GenreService;
import com.brash.digital_bookshelf.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public Genre getById(long id) {
        return genreRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format("Genre with id: %s -- is not found", id)
                        )
                );
    }

    @Override
    public void add(String name) {
        Genre genre = new Genre().setName(name);
        genreRepository.save(genre);
    }

    @Transactional
    @Override
    public void delete(String name) {
        genreRepository.deleteByName(name);
    }

    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    @Override
    public List<Genre> getByNames(List<String> names) {
        List<Genre> genres = new ArrayList<>();
        for (String name : names) {
            genres.add(
                    genreRepository.findByName(name).orElseThrow(() ->
                            new ResourceNotFoundException(
                                    String.format("Genre with name: %s -- is not found", name)
                            )
                    )
            );
        }
        return genres;
    }
}
