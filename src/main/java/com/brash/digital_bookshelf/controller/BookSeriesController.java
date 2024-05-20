package com.brash.digital_bookshelf.controller;

import com.brash.digital_bookshelf.data.entity.BookSeries;
import com.brash.digital_bookshelf.data.service.BookSeriesService;
import com.brash.digital_bookshelf.dto.BasicApiResponse;
import com.brash.digital_bookshelf.dto.EmptyApiResponse;
import com.brash.digital_bookshelf.dto.bookSeries.BookSeriesDto;
import com.brash.digital_bookshelf.utils.mappers.BookSeriesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookSeriesController {

    private final BookSeriesService bookSeriesService;

    private final BookSeriesMapper bookSeriesMapper;

    @GetMapping("/public/bookSeries/{id}")
    public ResponseEntity<BasicApiResponse<BookSeriesDto>> get(@PathVariable long id) {
        BookSeries series = bookSeriesService.getById(id);
        return ResponseEntity.ok(new BasicApiResponse<>(bookSeriesMapper.toDto(series)));
    }

    @PostMapping("/private/bookSeries")
    public ResponseEntity<EmptyApiResponse> add(@RequestParam String name, @RequestParam String description) {
        bookSeriesService.add(name, description);
        return ResponseEntity.ok(new EmptyApiResponse());
    }

    @PostMapping("/private/bookSeries/update")
    public ResponseEntity<EmptyApiResponse> update(
            @RequestParam long id,
            @RequestParam String name,
            @RequestParam String description
    ) {
        bookSeriesService.update(id, name, description);
        return ResponseEntity.ok(new EmptyApiResponse());
    }

    @PostMapping("/private/bookSeries/delete")
    public ResponseEntity<EmptyApiResponse> delete(@RequestParam long id) {
        bookSeriesService.delete(id);
        return ResponseEntity.ok(new EmptyApiResponse());
    }

    @GetMapping("/private/bookSeries/my")
    public ResponseEntity<BasicApiResponse<List<BookSeriesDto>>> mySeries() {
        List<BookSeries> series = bookSeriesService.mySeries();
        return ResponseEntity.ok(
                new BasicApiResponse<>(
                        bookSeriesMapper.toDto(series)
                )
        );
    }

    @GetMapping("/public/bookSeries/author/{id}")
    public ResponseEntity<BasicApiResponse<List<BookSeriesDto>>> seriesByAuthor(@PathVariable long id) {
        List<BookSeries> series = bookSeriesService.authorSeries(id);
        return ResponseEntity.ok(
                new BasicApiResponse<>(
                        bookSeriesMapper.toDto(series)
                )
        );
    }
}
