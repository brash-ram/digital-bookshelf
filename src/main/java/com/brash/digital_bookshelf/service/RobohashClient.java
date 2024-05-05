package com.brash.digital_bookshelf.service;

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
    public MultipartFile getImage(String hash) {
        ResponseEntity<MultipartFile> response = robohashRestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/" + hash)
                        .queryParam("set", set)
                        .build()
                )
                .retrieve()
                .toEntity(MultipartFile.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            MultipartFile file = response.getBody();
            assert file != null;
            return file;
        } else {
            log.error(response.getStatusCode().toString());
            return null;
        }
    }
}
