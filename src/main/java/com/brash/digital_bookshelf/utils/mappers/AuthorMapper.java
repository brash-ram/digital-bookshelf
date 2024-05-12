package com.brash.digital_bookshelf.utils.mappers;

import com.brash.digital_bookshelf.data.entity.AuthorInfo;
import com.brash.digital_bookshelf.data.entity.Book;
import com.brash.digital_bookshelf.data.entity.BookSeries;
import com.brash.digital_bookshelf.dto.author.AuthorInfoDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuthorMapper {


    private final ModelMapper modelMapper;

    public AuthorInfoDto toDto(AuthorInfo author) {
        AuthorInfoDto dto = modelMapper.map(author, AuthorInfoDto.class);
        dto.setBookIds(author.getBooks().stream().map(Book::getId).collect(Collectors.toSet()));
        dto.setSeriesIds(author.getSeries().stream().map(BookSeries::getId).collect(Collectors.toSet()));
        return dto;
    }
}
