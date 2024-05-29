package com.brash.digital_bookshelf.service;

import com.brash.digital_bookshelf.config.RabbitMQConfig;
import com.brash.digital_bookshelf.dto.recommendation.ItemRabbitDTO;
import com.brash.digital_bookshelf.dto.recommendation.MarkRabbitDTO;
import com.brash.digital_bookshelf.dto.recommendation.UserRabbitDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class RecommendationRabbitProducer {

    private final RabbitTemplate rabbitTemplate;

    private final RabbitMQConfig rabbitMQConfig;

    public void sendItem(ItemRabbitDTO dto) {
        send(dto);
    }

    public void sendUser(UserRabbitDTO dto) {
        send(dto);
    }

    public void removeItem(ItemRabbitDTO dto) {
        send(dto);
    }

    public void removeUser(UserRabbitDTO dto) {
        send(dto);
    }

    public void sendMark(MarkRabbitDTO dto) {
        send(dto);
    }

    private void send(Object data) {
        try {
            rabbitTemplate.convertAndSend(rabbitMQConfig.exchange(), rabbitMQConfig.queue(), data);
            log.info("Rabbit request add data - " + data);
        } catch (Exception e) {
            log.error(data.toString() + " --- " +  e.getMessage());
        }
    }
}
