package com.minproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MinProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(MinProjectApplication.class, args);
	}
}
