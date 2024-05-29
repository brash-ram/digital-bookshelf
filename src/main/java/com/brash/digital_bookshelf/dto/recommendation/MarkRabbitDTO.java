package com.brash.digital_bookshelf.dto.recommendation;

public record MarkRabbitDTO(
        long userId,
        long itemId,
        double mark
) {
}
