package com.ahuggins.spring_data_jpa_demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ahuggins.spring_data_jpa_demo.repositories.MovieRepository;

@SpringBootApplication
public class SpringDataJpaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaDemoApplication.class, args);
	}

	// @Bean
	// CommandLineRunner commandLineRunner(MovieRepository repo){
	// 	return (args) ->{
	// 		System.out.println(repo.findAll());
	// 	};
	// }
}
