package com.brash.digital_bookshelf.controller;

import com.brash.digital_bookshelf.data.entity.Tag;
import com.brash.digital_bookshelf.data.service.TagService;
import com.brash.digital_bookshelf.dto.BasicApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public/tag")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping("/all")
    public ResponseEntity<BasicApiResponse<List<String>>> all() {
        List<Tag> tags = tagService.getAll();
        return ResponseEntity.ok(new BasicApiResponse<>(tags.stream().map(Tag::getName).toList()));
    }
}
