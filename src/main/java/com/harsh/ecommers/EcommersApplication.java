package com.harsh.ecommers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommersApplication {

	public static void main(String[] args) {
		System.out.println("http://localhost:8080");
		SpringApplication.run(EcommersApplication.class, args);
	}

}
