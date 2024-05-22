package com.brash.digital_bookshelf.controller;

import com.brash.digital_bookshelf.data.entity.Image;
import com.brash.digital_bookshelf.dto.BasicApiResponse;
import com.brash.digital_bookshelf.s3storage.S3File;
import com.brash.digital_bookshelf.s3storage.config.S3Properties;
import com.brash.digital_bookshelf.usecase.ImageUseCase;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLConnection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ImageController {

    private final ImageUseCase imageUseCase;

    @GetMapping("/public/image/{id}")
    public ResponseEntity<byte[]> getUserImage(@PathVariable("id") long id) {
        S3File image = imageUseCase.getImage(id);
        String contentType = URLConnection.guessContentTypeFromName("." + image.getFilename().split("\\.")[1]);

        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(image.getContent());
    }

    @PostMapping(value = "/private/image/book", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<BasicApiResponse<Long>> upload(HttpServletRequest req, @RequestParam String extension, @RequestBody byte[] file) throws IOException {
        Image image = imageUseCase.saveImage(extension, file);

        return ResponseEntity
                .ok(new BasicApiResponse<>(image.getId()));
    }

}
