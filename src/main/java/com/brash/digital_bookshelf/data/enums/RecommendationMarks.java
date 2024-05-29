package com.brash.digital_bookshelf.data.enums;

import lombok.Getter;

@Getter
public enum RecommendationMarks {
    OPEN_BOOK(1),
    ADD_TO_LIBRARY(2),
    BUY_BOOK(4),
    ADD_BOOK(6);

    private final int mark;

    RecommendationMarks(int mark) {
        this.mark = mark;
    }

}
