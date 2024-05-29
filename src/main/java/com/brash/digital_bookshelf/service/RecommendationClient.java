package com.brash.digital_bookshelf.service;

import com.brash.digital_bookshelf.dto.recommendation.RecommendedItems;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecommendationClient {

    private final RestClient filterRestClient;

    @SneakyThrows
    public RecommendedItems getRecommendedItems(int offset, int limit, long userId) {
        ResponseEntity<RecommendedItems> response = filterRestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/generated")
                        .queryParam("userId", userId)
                        .queryParam("offset", offset)
                        .queryParam("limit", limit)
                        .build()
                )
                .retrieve()
                .toEntity(RecommendedItems.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            log.info(response.getStatusCode().toString());
            RecommendedItems items = response.getBody();
            log.info(items.toString());
            return items;
        } else {
            log.error(response.getStatusCode().toString());
            log.error("DATA " + offset + limit + userId);
            return null;
        }
    }

    @SneakyThrows
    public RecommendedItems getSimilar(int offset, int limit, long bookId) {
        ResponseEntity<RecommendedItems> response = filterRestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/item")
                        .queryParam("itemId", bookId)
                        .queryParam("offset", offset)
                        .queryParam("limit", limit)
                        .build()
                )
                .retrieve()
                .toEntity(RecommendedItems.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            log.info(response.getStatusCode().toString());
            RecommendedItems items = response.getBody();
            log.info(items.toString());
            return items;
        } else {
            log.error(response.getStatusCode().toString());
            log.error("DATA " + offset + limit + bookId);
            return null;
        }
    }
}
