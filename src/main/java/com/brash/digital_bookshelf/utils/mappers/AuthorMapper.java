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

    private final BookMapper bookMapper;

    private final BookSeriesMapper bookSeriesMapper;

    public AuthorInfoDto toDto(AuthorInfo author) {
        return modelMapper.map(author, AuthorInfoDto.class)
                .setBooks(author.getBooks().stream().map(bookMapper::toListItem).toList())
                .setSeries(author.getSeries().stream().map(bookSeriesMapper::toSimple).toList())
                .setUserId(author.getUser().getId());
    }
}
