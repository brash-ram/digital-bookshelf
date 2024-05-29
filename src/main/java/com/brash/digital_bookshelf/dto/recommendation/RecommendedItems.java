package com.brash.digital_bookshelf.dto.recommendation;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RecommendedItems(
        @NotNull List<Long> ids
) {
}
