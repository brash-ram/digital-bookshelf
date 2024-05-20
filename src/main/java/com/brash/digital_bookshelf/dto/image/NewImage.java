package com.brash.digital_bookshelf.dto.image;

public record NewImage(
        String extension,
        byte[] content
) {
}
