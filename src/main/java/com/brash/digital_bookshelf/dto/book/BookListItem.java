package com.brash.digital_bookshelf.dto.book;

import com.brash.digital_bookshelf.data.enums.PriceType;
import com.brash.digital_bookshelf.dto.image.ImageDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.brash.digital_bookshelf.data.entity.Book}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BookListItem implements Serializable {
    private Long id;
    private ImageDTO cover;
    private List<String> genreNames;
    private String authorName;
    private String series;
    private String name;
    private String description;
    private PriceType priceType;
    private Long price;
    private DateTime lastUpdate;
}