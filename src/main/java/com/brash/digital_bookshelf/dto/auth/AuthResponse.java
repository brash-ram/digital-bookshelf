package com.brash.digital_bookshelf.dto.auth;

public record AuthResponse(

    String accessToken,

    String refreshToken

) {}
