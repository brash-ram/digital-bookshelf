package com.brash.digital_bookshelf.dto.bookSeries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.brash.digital_bookshelf.data.entity.BookSeries}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookSeriesSimpleDto implements Serializable {
    private Long id;
    private String name;
    private String description;
}