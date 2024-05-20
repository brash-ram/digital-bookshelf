package com.brash.digital_bookshelf.dto.bookSeries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * DTO for {@link com.brash.digital_bookshelf.data.entity.BookSeries}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BookSeriesDto implements Serializable {
    private Long id;
    private Long authorId;
    private String name;
    private String description;
    private List<Long> bookIds;
}