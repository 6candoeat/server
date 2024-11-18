package com._candoit.drfood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DrfoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrfoodApplication.class, args);
	}

}
