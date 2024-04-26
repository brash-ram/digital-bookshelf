package com.brash.digital_bookshelf.dto.auth;

import org.springframework.lang.NonNull;

public record SignInRequest(

    @NonNull String username,

    @NonNull String password

) {}
