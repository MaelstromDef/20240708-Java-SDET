package com.ahuggins.message_queue_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MessageQueueDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageQueueDemoApplication.class, args);
	}

}
