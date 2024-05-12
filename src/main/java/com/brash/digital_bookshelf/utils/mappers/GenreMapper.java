package com.brash.digital_bookshelf.utils.mappers;

import com.brash.digital_bookshelf.data.entity.Genre;
import com.brash.digital_bookshelf.dto.genre.GenreDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GenreMapper {

    public GenreDto map(Genre genre) {
        return new GenreDto(genre.getName());
    }
}
