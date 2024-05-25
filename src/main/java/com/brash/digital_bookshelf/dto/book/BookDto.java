package com.brash.digital_bookshelf.dto.book;

import com.brash.digital_bookshelf.data.enums.PriceType;
import com.brash.digital_bookshelf.dto.bookSeries.BookSeriesDto;
import com.brash.digital_bookshelf.dto.bookSeries.BookSeriesSimpleDto;
import com.brash.digital_bookshelf.dto.image.ImageDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * DTO for {@link com.brash.digital_bookshelf.data.entity.Book}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BookDto implements Serializable {
    private Long id;
    private Long authorId;
    private ImageDTO cover;
    private List<String> genreNames;
    private List<String> tagNames;
    private BookSeriesSimpleDto series;
    private String name;
    private String description;
    private PriceType priceType;
    private Long price;
    private Timestamp lastUpdate;
    private Date createdAt;
}