package com.brash.digital_bookshelf.controller;

import com.brash.digital_bookshelf.data.entity.Book;
import com.brash.digital_bookshelf.data.service.BookService;
import com.brash.digital_bookshelf.dto.BasicApiResponse;
import com.brash.digital_bookshelf.dto.EmptyApiResponse;
import com.brash.digital_bookshelf.dto.book.BookDto;
import com.brash.digital_bookshelf.dto.book.BookListItem;
import com.brash.digital_bookshelf.dto.book.CreateBook;
import com.brash.digital_bookshelf.usecase.BookUseCase;
import com.brash.digital_bookshelf.utils.mappers.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookController {

    private final BookUseCase bookUseCase;

    private final BookService bookService;

    private final BookMapper bookMapper;

    @PostMapping("/private/book/add")
    public ResponseEntity<BasicApiResponse<Long>> createBook(@RequestBody CreateBook book) {
        long id = bookUseCase.createNewBook(book);
        return ResponseEntity.ok(new BasicApiResponse<>(id));
    }

    @PostMapping("/private/book/upload")
    public ResponseEntity<EmptyApiResponse> uploadBookFile(
            @RequestParam long id,
            @RequestParam String extension,
            @RequestBody byte[] file
    ) {
        bookUseCase.uploadBookFile(extension, id, file);
        return ResponseEntity.ok(new EmptyApiResponse());
    }

    @GetMapping("/public/book/{id}")
    public ResponseEntity<BasicApiResponse<BookDto>> get(@PathVariable long id) {
        Book book = bookService.getById(id);
        return ResponseEntity.ok(new BasicApiResponse<>(bookMapper.toDto(book)));
    }

    @GetMapping("/private/book/my")
    public ResponseEntity<BasicApiResponse<List<BookListItem>>> my() {
        List<Book> book = bookUseCase.myBooks();
        return ResponseEntity.ok(new BasicApiResponse<>(bookMapper.toListItems(book)));
    }

    @GetMapping("/public/book/author/{id}")
    public ResponseEntity<BasicApiResponse<List<BookListItem>>> getByAuthor(@PathVariable long id) {
        List<Book> book = bookUseCase.getBooksFromAuthor(id);
        return ResponseEntity.ok(new BasicApiResponse<>(bookMapper.toListItems(book)));
    }
}
