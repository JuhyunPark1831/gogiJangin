package com.project.gogiJangin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GogiJanginApplication {

	public static void main(String[] args) {
		SpringApplication.run(GogiJanginApplication.class, args);
	}

}
