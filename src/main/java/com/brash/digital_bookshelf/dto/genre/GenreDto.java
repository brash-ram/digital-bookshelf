package com.brash.digital_bookshelf.dto.genre;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * DTO for {@link com.brash.digital_bookshelf.data.entity.Genre}
 */
public record GenreDto(
        String name
) {}