package com.brash.digital_bookshelf.dto.author;

import com.brash.digital_bookshelf.dto.book.BookListItem;
import com.brash.digital_bookshelf.dto.bookSeries.BookSeriesDto;
import com.brash.digital_bookshelf.dto.bookSeries.BookSeriesSimpleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * DTO for {@link com.brash.digital_bookshelf.data.entity.AuthorInfo}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class AuthorInfoDto implements Serializable {
    private Long id;
    private Long userId;
    private List<BookListItem> books;
    private List<BookSeriesSimpleDto> series;
}