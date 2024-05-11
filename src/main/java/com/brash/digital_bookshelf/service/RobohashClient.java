package com.brash.digital_bookshelf.service;

import com.brash.digital_bookshelf.s3storage.S3File;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Slf4j
@Service
public class RobohashClient {

    private final RestClient robohashRestClient;

    @Value("${robohash.set}")
    private String set;

    @SneakyThrows
    public S3File getImage(String hash) {
        ResponseEntity<byte[]> response = robohashRestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/" + hash)
                        .queryParam("set", set)
                        .build()
                )
                .retrieve()
                .toEntity(byte[].class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return new S3File(response.getBody());
        } else {
            log.error(response.getStatusCode().toString());
            return null;
        }
    }
}
