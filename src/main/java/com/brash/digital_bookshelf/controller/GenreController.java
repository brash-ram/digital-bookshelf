package com.brash.digital_bookshelf.controller;

import com.brash.digital_bookshelf.data.entity.Genre;
import com.brash.digital_bookshelf.data.service.GenreService;
import com.brash.digital_bookshelf.dto.BasicApiResponse;
import com.brash.digital_bookshelf.dto.EmptyApiResponse;
import com.brash.digital_bookshelf.dto.genre.GenreDto;
import com.brash.digital_bookshelf.utils.mappers.GenreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    private final GenreMapper genreMapper;

    @GetMapping("/public/genre/{id}")
    public ResponseEntity<BasicApiResponse<GenreDto>> get(@PathVariable long id) {
        Genre genre = genreService.getById(id);
        return ResponseEntity.ok(new BasicApiResponse<>(genreMapper.map(genre)));
    }

    @PostMapping("/admin/genre")
    public ResponseEntity<EmptyApiResponse> add(@RequestParam String name) {
        genreService.add(name);
        return ResponseEntity.ok(new EmptyApiResponse());
    }

    @GetMapping("/public/genre/all")
    public ResponseEntity<BasicApiResponse<List<GenreDto>>> get() {
        List<Genre> genres = genreService.getAll();
        return ResponseEntity.ok(
                new BasicApiResponse<>(
                        genres.stream()
                                .map(genreMapper::map)
                                .toList()
                )
        );
    }
}
