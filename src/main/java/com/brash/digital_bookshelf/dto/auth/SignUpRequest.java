package com.brash.digital_bookshelf.dto.auth;

import org.springframework.lang.NonNull;

public record SignUpRequest(

    @NonNull String username,

    @NonNull String fullName
) {}
