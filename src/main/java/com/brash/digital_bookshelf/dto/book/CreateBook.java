package com.brash.digital_bookshelf.dto.book;

import com.brash.digital_bookshelf.data.enums.PriceType;
import com.brash.digital_bookshelf.dto.FileDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBook {
    private long cover;

    private List<String> genres;
    private List<String> tags;

    private long seriesId;
    private String name;
    private String extension;
    private String description;
    private PriceType priceType;
    private Long price;
}
