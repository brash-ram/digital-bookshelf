package com.brash.digital_bookshelf.controller;

import com.brash.digital_bookshelf.data.entity.AuthorInfo;
import com.brash.digital_bookshelf.data.service.AuthorService;
import com.brash.digital_bookshelf.dto.BasicApiResponse;
import com.brash.digital_bookshelf.dto.author.AuthorInfoDto;
import com.brash.digital_bookshelf.utils.AuthUtils;
import com.brash.digital_bookshelf.utils.mappers.AuthorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    private final AuthorMapper authorMapper;

    private final AuthUtils authUtils;

    @GetMapping("/public/author/{id}")
    public ResponseEntity<BasicApiResponse<AuthorInfoDto>> getAuthor(@PathVariable long id) {
        AuthorInfo authorInfo = authorService.getById(id);
        return ResponseEntity.ok(new BasicApiResponse<>(authorMapper.toDto(authorInfo)));
    }

    @GetMapping("/public/author")
    public ResponseEntity<BasicApiResponse<AuthorInfoDto>> getAuthorByUserId(@RequestParam long userId) {
        AuthorInfo authorInfo = authorService.getByUserId(userId);
        return ResponseEntity.ok(new BasicApiResponse<>(authorMapper.toDto(authorInfo)));
    }

    @GetMapping("/public/author/me")
    public ResponseEntity<BasicApiResponse<AuthorInfoDto>> me() {
        return ResponseEntity.ok(new BasicApiResponse<>(authorMapper.toDto(authUtils.getUserEntity().getAuthorInfo())));
    }
}
