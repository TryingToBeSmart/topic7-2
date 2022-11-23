package com.gcu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@EnableEurekaClient
@SpringBootApplication
public class Topic72aApplication {

	public static void main(String[] args) {
		SpringApplication.run(Topic72aApplication.class, args);
	}

}
