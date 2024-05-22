package com.brash.digital_bookshelf.utils.mappers;

import com.brash.digital_bookshelf.data.entity.Book;
import com.brash.digital_bookshelf.data.entity.Genre;
import com.brash.digital_bookshelf.data.entity.Tag;
import com.brash.digital_bookshelf.data.service.ImageService;
import com.brash.digital_bookshelf.dto.book.BookDto;
import com.brash.digital_bookshelf.dto.book.BookListItem;
import com.brash.digital_bookshelf.dto.image.ImageDTO;
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

    public BookDto toDto(Book book) {
        BookDto dto = modelMapper.map(book, BookDto.class);
        dto
                .setGenreNames(book.getGenres().stream().map(Genre::getName).toList())
                .setTagNames(book.getTags().stream().map(Tag::getName).toList())
                .setSeries(bookSeriesMapper.toSimple(book.getSeries()));
        return dto;
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

    public List<BookDto> toDto(List<Book> books) {
        return books.stream().map(this::toDto).toList();
    }

    public List<BookListItem> toListItems(List<Book> books) {
        return books.stream().map(this::toListItem).toList();
    }
}
