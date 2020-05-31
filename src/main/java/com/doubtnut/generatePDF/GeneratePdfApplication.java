package com.doubtnut.generatePDF;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GeneratePdfApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeneratePdfApplication.class, args);
	}

}
