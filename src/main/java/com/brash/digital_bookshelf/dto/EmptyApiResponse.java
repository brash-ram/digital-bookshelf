package com.brash.digital_bookshelf.dto;

import lombok.Data;

@Data
public class EmptyApiResponse {
    private boolean error = false;
    private boolean isEmpty = false;
    private boolean result = true;
}
