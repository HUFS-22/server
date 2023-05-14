package com.example.vwx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class VwxApplication {

	public static void main(String[] args) {
		SpringApplication.run(VwxApplication.class, args);
	}

}
