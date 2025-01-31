package com.example.student_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class StudentManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}

	// Bean for RestTemplate to enable REST API calls between applications
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}