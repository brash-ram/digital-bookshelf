package com.brash.digital_bookshelf.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "filter.rabbit", ignoreUnknownFields = false)
public record RabbitMQConfig(
        String queue,
        String exchange
) {
}
