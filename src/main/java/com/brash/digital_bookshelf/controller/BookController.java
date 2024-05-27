package com.brash.digital_bookshelf.controller;

import com.brash.digital_bookshelf.data.entity.Book;
import com.brash.digital_bookshelf.data.entity.PurchasedBook;
import com.brash.digital_bookshelf.data.service.BookService;
import com.brash.digital_bookshelf.data.service.UserService;
import com.brash.digital_bookshelf.dto.BasicApiResponse;
import com.brash.digital_bookshelf.dto.EmptyApiResponse;
import com.brash.digital_bookshelf.dto.book.BookDto;
import com.brash.digital_bookshelf.dto.book.BookListItem;
import com.brash.digital_bookshelf.dto.book.CreateBook;
import com.brash.digital_bookshelf.dto.book.PurchasedBookDto;
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

    @PostMapping("/private/book/library/add")
    public ResponseEntity<EmptyApiResponse> addToLibrary(@RequestParam long id) {
        bookUseCase.addToLibrary(id);
        return ResponseEntity.ok(new EmptyApiResponse());
    }

    @GetMapping("/private/book/library/my")
    public ResponseEntity<BasicApiResponse<List<BookListItem>>> getMyLibrary() {
        List<Book> book = bookUseCase.getMyLibrary();
        return ResponseEntity.ok(new BasicApiResponse<>(bookMapper.toListItems(book)));
    }

    @GetMapping("/public/book/library/author/{id}")
    public ResponseEntity<BasicApiResponse<List<BookListItem>>> getLibraryAuthor(@PathVariable long id) {
        List<Book> book = bookUseCase.getLibraryByAuthor(id);
        return ResponseEntity.ok(new BasicApiResponse<>(bookMapper.toListItems(book)));
    }

    @GetMapping("/private/book/purchased")
    public ResponseEntity<BasicApiResponse<List<PurchasedBookDto>>> getPurchasedBooks() {
        List<PurchasedBook> book = bookUseCase.getMyPurchasedBooks();
        return ResponseEntity.ok(new BasicApiResponse<>(bookMapper.toPurchasedBook(book)));
    }

    @PostMapping("/private/book/buy/{id}")
    public ResponseEntity<EmptyApiResponse> buyBook(@PathVariable long id) {
        bookUseCase.buy(id);
        return ResponseEntity.ok(new EmptyApiResponse());
    }
}
