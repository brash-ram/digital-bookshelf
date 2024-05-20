package com.brash.digital_bookshelf.utils.mappers;

import com.brash.digital_bookshelf.data.entity.Book;
import com.brash.digital_bookshelf.data.entity.BookSeries;
import com.brash.digital_bookshelf.dto.bookSeries.BookSeriesDto;
import com.brash.digital_bookshelf.dto.bookSeries.BookSeriesSimpleDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookSeriesMapper {

    private final ModelMapper modelMapper;

    public BookSeriesDto toDto(BookSeries series) {
        BookSeriesDto dto = modelMapper.map(series, BookSeriesDto.class);
        dto.setAuthorId(series.getAuthor().getId());
        dto.setBookIds(series.getBooks().stream().map(Book::getId).toList());
        return dto;
    }

    public BookSeriesSimpleDto toSimple(BookSeries series) {
        return modelMapper.map(series, BookSeriesSimpleDto.class);
    }

    public List<BookSeriesDto> toDto(List<BookSeries> series) {
        return series.stream().map(this::toDto).toList();
    }

}
