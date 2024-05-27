package com.brash.digital_bookshelf.utils.mappers;

import com.brash.digital_bookshelf.data.entity.*;
import com.brash.digital_bookshelf.data.service.ImageService;
import com.brash.digital_bookshelf.data.service.UserService;
import com.brash.digital_bookshelf.dto.book.BookDto;
import com.brash.digital_bookshelf.dto.book.BookListItem;
import com.brash.digital_bookshelf.dto.book.PurchasedBookDto;
import com.brash.digital_bookshelf.dto.image.ImageDTO;
import com.brash.digital_bookshelf.utils.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookMapper {

    private final ModelMapper modelMapper;

    private final BookSeriesMapper bookSeriesMapper;

    private final ImageMapper imageMapper;

    private final UserService userService;

    private final AuthUtils authUtils;

    public BookDto toDto(Book book) {
        User user = userService.getById(authUtils.getUserEntity().getId());
        BookDto dto = modelMapper.map(book, BookDto.class);
        dto
                .setGenreNames(book.getGenres().stream().map(Genre::getName).toList())
                .setTagNames(book.getTags().stream().map(Tag::getName).toList())
                .setCover(imageMapper.toDto(book.getCover()))
                .setSeries(bookSeriesMapper.toSimple(book.getSeries()))
                .setInLibrary(user.getLibrary().contains(book));
        return dto;
    }

    public PurchasedBookDto toPurchasedBook(PurchasedBook book) {
        PurchasedBookDto dto = modelMapper.map(book, PurchasedBookDto.class);
        dto.setBook(toListItem(book.getBook()));
        return dto;
    }

    public List<PurchasedBookDto> toPurchasedBook(List<PurchasedBook> book) {
        return book.stream().map(this::toPurchasedBook).toList();
    }

    public BookListItem toListItem(Book book) {
        BookListItem dto = modelMapper.map(book, BookListItem.class);
        dto
                .setGenreNames(book.getGenres().stream().map(Genre::getName).toList())
                .setSeries(book.getSeries() != null ? book.getSeries().getName() : null)
                .setCover(imageMapper.toDto(book.getCover()))
                .setAuthorName(book.getAuthor().getUser().getName());
        return dto;
    }

    public List<BookListItem> toListItems(List<Book> books) {
        return books.stream().map(this::toListItem).toList();
    }
}
