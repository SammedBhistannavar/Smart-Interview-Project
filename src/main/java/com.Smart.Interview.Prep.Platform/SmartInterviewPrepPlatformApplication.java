package com.Smart.Interview.Prep.Platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class SmartInterviewPrepPlatformApplication {

	public static void main(String[] args) {
		log.info("Starting the Application...");
		SpringApplication.run(SmartInterviewPrepPlatformApplication.class, args);
	}
}