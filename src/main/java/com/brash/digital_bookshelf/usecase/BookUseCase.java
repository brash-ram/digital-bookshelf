package com.brash.digital_bookshelf.usecase;

import com.brash.digital_bookshelf.data.entity.Book;
import com.brash.digital_bookshelf.data.entity.PurchasedBook;
import com.brash.digital_bookshelf.dto.book.CreateBook;

import java.util.List;

public interface BookUseCase {
    long createNewBook(CreateBook newBook);

    List<Book> myBooks();

    List<Book> getBooksFromAuthor(long authorId);

    void uploadBookFile(String extension, long bookId, byte[] file);

    void addToLibrary(long id);
    void buy(long id);

    List<Book> getMyLibrary();

    List<Book> getLibraryByAuthor(long authorId);

    List<PurchasedBook> getMyPurchasedBooks();

    List<Book> getBooksByGenre(String genre);

    List<Book> getRecommendedBooks();

    List<Book> getHomeRecommendedBooks();

    List<Book> getSimilarBooks(long id);

}
