package com.test.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.test.config.database.PersistenceConfig;
import com.test.config.security.SecurityConfig;

@SpringBootApplication(scanBasePackageClasses = { PersistenceConfig.class, SecurityConfig.class })
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}
}
