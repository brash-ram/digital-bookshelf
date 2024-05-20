package com.brash.digital_bookshelf.utils.mappers;

import com.brash.digital_bookshelf.data.entity.Book;
import com.brash.digital_bookshelf.data.entity.Genre;
import com.brash.digital_bookshelf.data.entity.Tag;
import com.brash.digital_bookshelf.dto.book.BookDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookMapper {

    private final ModelMapper modelMapper;

    private final BookSeriesMapper bookSeriesMapper;

    public BookDto toDto(Book book) {
        BookDto dto = modelMapper.map(book, BookDto.class);
        dto
                .setGenreNames(book.getGenres().stream().map(Genre::getName).toList())
                .setTagNames(book.getTags().stream().map(Tag::getName).toList())
                .setSeries(bookSeriesMapper.toSimple(book.getSeries()));
        return dto;
    }

    public List<BookDto> toDto(List<Book> books) {
        return books.stream().map(this::toDto).toList();
    }
}
