package com.brash.digital_bookshelf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableJpaRepositories
@EnableWebMvc
@EnableScheduling
public class DigitalBookshelfApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalBookshelfApplication.class, args);
	}

}
