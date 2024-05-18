package com.brash.digital_bookshelf.utils.mappers;

import com.brash.digital_bookshelf.data.entity.BookSeries;
import com.brash.digital_bookshelf.dto.bookSeries.BookSeriesDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookSeriesMapper {

    private final ModelMapper modelMapper;

    public BookSeriesDto toDto(BookSeries series) {
        return modelMapper.map(series, BookSeriesDto.class);
    }

    public List<BookSeriesDto> toDto(List<BookSeries> series) {
        return series.stream().map(this::toDto).toList();
    }

}
