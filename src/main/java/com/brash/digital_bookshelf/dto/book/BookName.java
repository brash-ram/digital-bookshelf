package com.brash.digital_bookshelf.dto.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * DTO for {@link com.brash.digital_bookshelf.data.entity.Book}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BookName implements Serializable {
    private Long id;
    private String name;
}