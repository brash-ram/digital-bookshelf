package com.brash.digital_bookshelf.service;

import com.brash.digital_bookshelf.data.entity.Book;
import com.brash.digital_bookshelf.data.entity.Image;
import com.brash.digital_bookshelf.data.entity.User;
import com.brash.digital_bookshelf.data.repository.BookRepository;
import com.brash.digital_bookshelf.data.repository.UserRepository;
import com.brash.digital_bookshelf.dto.recommendation.ItemRabbitDTO;
import com.brash.digital_bookshelf.dto.recommendation.UserRabbitDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@EnableAsync
public class RecommendationSyncScheduler {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final RecommendationRabbitProducer recommendationRabbitProducer;

    @Async
    @Scheduled(fixedDelay = 100000000L, initialDelay = 5000L)
    public void updateRecommendations() {
        List<Book> books = bookRepository.findAll();
        List<User> users = userRepository.findAll();

        try {
            for (Book book : books) {
                recommendationRabbitProducer.sendItem(new ItemRabbitDTO(book.getId()));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        try {
            for (User user : users) {
                recommendationRabbitProducer.sendUser(new UserRabbitDTO(user.getId()));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        log.info("Finish synchronizing the database with the recommendation service.");
    }
}
