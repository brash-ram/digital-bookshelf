package com.brash.digital_bookshelf.controller;

import com.brash.digital_bookshelf.s3storage.S3File;
import com.brash.digital_bookshelf.s3storage.config.S3Properties;
import com.brash.digital_bookshelf.usecase.ImageUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLConnection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public/image")
public class ImageController {

    private final ImageUseCase imageUseCase;

    private final S3Properties s3Properties;

    @GetMapping("/user/{id}")
    public ResponseEntity<byte[]> getById(@PathVariable("id") long id) {
        S3File image = imageUseCase.getImage(id, s3Properties.getProfileImageBucket());
        String contentType = URLConnection.guessContentTypeFromName("." + image.getFilename().split("\\.")[1]);

        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(image.getContent());
    }

}
