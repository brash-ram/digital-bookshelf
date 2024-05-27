package com.brash.digital_bookshelf.data.service.impl;

import com.brash.digital_bookshelf.data.entity.PurchasedBook;
import com.brash.digital_bookshelf.data.repository.PurchasedBookRepository;
import com.brash.digital_bookshelf.data.service.PurchasedBookService;
import com.brash.digital_bookshelf.exception.ResourceNotFoundException;
import com.brash.digital_bookshelf.utils.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchasedBookServiceImpl implements PurchasedBookService {

    private final PurchasedBookRepository purchasedBookRepository;

    @Override
    public PurchasedBook getById(long id) {
        return purchasedBookRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format("Purchased book with id: %s -- is not found", id)
                        )
                );
    }
}
