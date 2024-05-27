package com.brash.digital_bookshelf.usecase.impl;

import com.brash.digital_bookshelf.data.entity.*;
import com.brash.digital_bookshelf.data.repository.BookRepository;
import com.brash.digital_bookshelf.data.repository.PurchasedBookRepository;
import com.brash.digital_bookshelf.data.repository.UserRepository;
import com.brash.digital_bookshelf.data.service.*;
import com.brash.digital_bookshelf.dto.book.CreateBook;
import com.brash.digital_bookshelf.s3storage.S3File;
import com.brash.digital_bookshelf.s3storage.config.S3Properties;
import com.brash.digital_bookshelf.service.FileService;
import com.brash.digital_bookshelf.usecase.BookUseCase;
import com.brash.digital_bookshelf.usecase.ImageUseCase;
import com.brash.digital_bookshelf.utils.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutorService;

@Service
@RequiredArgsConstructor
public class BookUseCaseImpl implements BookUseCase {

    private final BookRepository bookRepository;

    private final BookService bookService;

    private final PurchasedBookRepository purchasedBookRepository;

    private final FileService fileService;

    private final S3Properties s3Properties;

    private final AuthUtils authUtils;

    private final GenreService genreService;

    private final TagService tagService;

    private final ImageUseCase imageUseCase;

    private final AuthorService authorService;

    private final ImageService imageService;

    private final UserService userService;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public long createNewBook(CreateBook newBook) {
        User user = authUtils.getUserEntity();

        Image cover = imageService.getById(newBook.getCover());

        Book book = new Book()
                .setAuthor(user.getAuthorInfo())
                .setGenres(new HashSet<>(genreService.getByNames(newBook.getGenres())))
                .setTags(new HashSet<>(tagService.getAllByNames(newBook.getTags())))
                .setName(newBook.getName())
                .setDescription(newBook.getDescription())
                .setPrice(newBook.getPrice())
                .setPriceType(newBook.getPriceType())
                .setCover(cover)
                .setExtension(newBook.getExtension());

        book = bookRepository.save(book);

        user.addBookToLibrary(book);
        userRepository.save(user);

        return book.getId();
    }

    @Transactional
    @Override
    public List<Book> myBooks() {
        User user = userService.getById(authUtils.getUserEntity().getId());
        return new ArrayList<>(user.getAuthorInfo().getBooks());
    }

    @Transactional
    @Override
    public List<Book> getBooksFromAuthor(long authorId) {
        AuthorInfo authorInfo = authorService.getById(authorId);
        return new ArrayList<>(authorInfo.getBooks());
    }

    @Transactional
    @Override
    public void uploadBookFile(String extension, long bookId, byte[] file) {
        Book book = bookService.getById(bookId);
        book.setExtension(extension);

        fileService.save(
                new S3File(book.getFilenameWithExtension(), file),
                s3Properties.getBookBucket()
        );

        bookRepository.save(book);
    }

    @Transactional
    @Override
    public void addToLibrary(long id) {
        User user = userService.getById(authUtils.getUserEntity().getId());
        Book book = bookService.getById(id);
        user.addBookToLibrary(book);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void buy(long id) {
        User user = userService.getById(authUtils.getUserEntity().getId());
        Book book = bookService.getById(id);
        PurchasedBook purchasedBook = new PurchasedBook()
                .setBook(book)
                .setUser(user)
                .setPrice(book.getPrice());
        user.addBookToLibrary(book);
        userRepository.save(user);
        purchasedBookRepository.save(purchasedBook);
    }

    @Transactional
    @Override
    public List<Book> getMyLibrary() {
        User user = userService.getById(authUtils.getUserEntity().getId());
        return new ArrayList<>(user.getLibrary());
    }

    @Transactional
    @Override
    public List<Book> getLibraryByAuthor(long authorId) {
        User user = userService.getById(authorId);
        return new ArrayList<>(user.getLibrary());
    }

    @Transactional
    @Override
    public List<PurchasedBook> getMyPurchasedBooks() {
        return purchasedBookRepository.findAllByUserId(authUtils.getUserEntity().getId());
    }
}
