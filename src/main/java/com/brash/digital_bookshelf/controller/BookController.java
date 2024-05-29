package com.brash.digital_bookshelf.controller;

import com.brash.digital_bookshelf.data.entity.Book;
import com.brash.digital_bookshelf.data.entity.PurchasedBook;
import com.brash.digital_bookshelf.data.enums.RecommendationMarks;
import com.brash.digital_bookshelf.data.service.BookService;
import com.brash.digital_bookshelf.dto.BasicApiResponse;
import com.brash.digital_bookshelf.dto.EmptyApiResponse;
import com.brash.digital_bookshelf.dto.book.*;
import com.brash.digital_bookshelf.dto.recommendation.MarkRabbitDTO;
import com.brash.digital_bookshelf.service.RecommendationRabbitProducer;
import com.brash.digital_bookshelf.usecase.BookUseCase;
import com.brash.digital_bookshelf.utils.AuthUtils;
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

    private final RecommendationRabbitProducer recommendation;

    private final AuthUtils authUtils;

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

        if (authUtils.getUserDetailsOrNull().isPresent()) {
            recommendation.sendMark(new MarkRabbitDTO(
                    authUtils.getUserEntity().getId(),
                    book.getId(),
                    RecommendationMarks.OPEN_BOOK.getMark()
            ));
        }

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

    @GetMapping("/public/book/genre")
    public ResponseEntity<BasicApiResponse<List<BookListItem>>> getBooksFromGenre(@RequestParam String genre) {
        List<Book> books = bookUseCase.getBooksByGenre(genre);
        return ResponseEntity.ok(new BasicApiResponse<>(bookMapper.toListItems(books)));
    }

    @GetMapping("/public/book/names")
    public ResponseEntity<BasicApiResponse<List<BookName>>> getBookNames() {
        List<Book> books = bookService.getBookNames();
        return ResponseEntity.ok(new BasicApiResponse<>(bookMapper.toName(books)));
    }

    @GetMapping("/public/book/last/home")
    public ResponseEntity<BasicApiResponse<List<BookListItem>>> getHomeLastBooks() {
        List<Book> books = bookService.getHomeLastBooks();
        return ResponseEntity.ok(new BasicApiResponse<>(bookMapper.toListItems(books)));
    }

    @GetMapping("/public/book/last/all")
    public ResponseEntity<BasicApiResponse<List<BookListItem>>> getLastBooks() {
        List<Book> books = bookService.getLastBooks();
        return ResponseEntity.ok(new BasicApiResponse<>(bookMapper.toListItems(books)));
    }

    @GetMapping("/public/book/search")
    public ResponseEntity<BasicApiResponse<List<BookListItem>>> search(@RequestParam String name) {
        List<Book> books = bookService.search(name);
        return ResponseEntity.ok(new BasicApiResponse<>(bookMapper.toListItems(books)));
    }

    @GetMapping("/private/book/rec/home")
    public ResponseEntity<BasicApiResponse<List<BookListItem>>> getHomeRecBooks() {
        List<Book> books = bookUseCase.getHomeRecommendedBooks();
        return ResponseEntity.ok(new BasicApiResponse<>(bookMapper.toListItems(books)));
    }

    @GetMapping("/private/book/rec/all")
    public ResponseEntity<BasicApiResponse<List<BookListItem>>> getRecBooks() {
        List<Book> books = bookUseCase.getRecommendedBooks();
        return ResponseEntity.ok(new BasicApiResponse<>(bookMapper.toListItems(books)));
    }

    @GetMapping("/public/book/similar")
    public ResponseEntity<BasicApiResponse<List<BookListItem>>> getSimilarBooks(@RequestParam long bookId) {
        List<Book> books = bookUseCase.getSimilarBooks(bookId);
        return ResponseEntity.ok(new BasicApiResponse<>(bookMapper.toListItems(books)));
    }
}
