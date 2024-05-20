package com.brash.digital_bookshelf.dto;

public record FileDto(
        String extension,
        byte[] content
) {
}
