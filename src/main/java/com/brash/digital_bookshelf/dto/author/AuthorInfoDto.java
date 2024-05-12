package com.brash.digital_bookshelf.dto.author;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
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
    private Set<Long> bookIds;
    private Set<Long> seriesIds;
}