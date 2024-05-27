package com.brash.digital_bookshelf.data.service;

import com.brash.digital_bookshelf.data.entity.BookSeries;
import com.brash.digital_bookshelf.data.entity.PurchasedBook;

public interface PurchasedBookService {
    PurchasedBook getById(long id);
}
