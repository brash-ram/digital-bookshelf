package com.brash.digital_bookshelf.dto.bookSeries;

import java.io.Serializable;

/**
 * DTO for {@link com.brash.digital_bookshelf.data.entity.BookSeries}
 */
public record BookSeriesDto(Long id, String name, String description) implements Serializable {
}