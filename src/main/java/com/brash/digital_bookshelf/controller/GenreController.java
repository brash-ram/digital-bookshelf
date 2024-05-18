package com.brash.digital_bookshelf.controller;

import com.brash.digital_bookshelf.data.entity.Genre;
import com.brash.digital_bookshelf.data.service.GenreService;
import com.brash.digital_bookshelf.dto.BasicApiResponse;
import com.brash.digital_bookshelf.dto.EmptyApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/public/genre/{id}")
    public ResponseEntity<BasicApiResponse<String>> get(@PathVariable long id) {
        Genre genre = genreService.getById(id);
        return ResponseEntity.ok(new BasicApiResponse<>(genre.getName()));
    }

    @PostMapping("/admin/genre")
    public ResponseEntity<EmptyApiResponse> add(@RequestParam String name) {
        genreService.add(name);
        return ResponseEntity.ok(new EmptyApiResponse());
    }

    @PostMapping("/admin/genre/delete")
    public ResponseEntity<EmptyApiResponse> delete(@RequestParam String name) {
        genreService.delete(name);
        return ResponseEntity.ok(new EmptyApiResponse());
    }

    @GetMapping("/public/genre/all")
    public ResponseEntity<BasicApiResponse<List<String>>> get() {
        List<Genre> genres = genreService.getAll();
        return ResponseEntity.ok(
                new BasicApiResponse<>(
                        genres.stream()
                                .map(Genre::getName)
                                .toList()
                )
        );
    }
}
