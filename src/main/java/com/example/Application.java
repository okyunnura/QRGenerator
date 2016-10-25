package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		try (ConfigurableApplicationContext context = SpringApplication.run(Application.class, args)) {
			Application application = context.getBean(Application.class);
			application.generate();
		}
	}

	private void generate() {
		logger.info("generate");
	}
}
