package com.skillstorm.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.skillstorm.demo.models.Director;
import com.skillstorm.demo.repositories.MovieRepository;

@SpringBootApplication
public class SpringDataJpaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaDemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(MovieRepository repo) {
		return (args) -> {
			System.out.println(repo.findById(1));
		};
	}

}
