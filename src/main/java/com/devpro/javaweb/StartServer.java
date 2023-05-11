package com.devpro.javaweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StartServer {

	public static void main(String[] args) {
		SpringApplication.run(StartServer.class, args);
		System.out.println("Website is running on: http://localhost:8081");
	}

}