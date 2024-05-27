package com.brash.digital_bookshelf.dto.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.brash.digital_bookshelf.data.entity.PurchasedBook}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PurchasedBookDto implements Serializable {
    private BookListItem book;
    private long price;
    private Date createdAt;
}