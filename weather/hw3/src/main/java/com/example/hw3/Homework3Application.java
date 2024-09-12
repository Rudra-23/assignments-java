package com.example.hw3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class Homework3Application {

	public static void main(String[] args) {
		SpringApplication.run(Homework3Application.class, args);
	}

}
