package com.brash.digital_bookshelf.usecase.impl;

import com.brash.digital_bookshelf.data.entity.AuthorInfo;
import com.brash.digital_bookshelf.data.entity.User;
import com.brash.digital_bookshelf.data.repository.AuthorInfoRepository;
import com.brash.digital_bookshelf.data.service.AuthorService;
import com.brash.digital_bookshelf.usecase.AuthorUseCase;
import com.brash.digital_bookshelf.utils.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthorUserCaseImpl implements AuthorUseCase {

    private final AuthorInfoRepository authorInfoRepository;

    private final AuthorService authorService;

    private final AuthUtils authUtils;
}
