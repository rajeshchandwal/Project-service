package com.teksAcademy.springDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.teksAcademy.springDemo.*"})
@EnableJpaRepositories("com.teksAcademy.springDemo.*")
@SpringBootApplication
public class InteractionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InteractionServiceApplication.class, args);
	}

}
