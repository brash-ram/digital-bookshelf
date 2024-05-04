package com.brash.digital_bookshelf.dto.user;

import jakarta.persistence.Column;

public record ChangeUserRefsRequest(
        String refVk,

        String refTg,

        String refSite,

        String refEmail
) {
}
