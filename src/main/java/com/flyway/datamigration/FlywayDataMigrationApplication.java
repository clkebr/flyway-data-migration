package com.flyway.datamigration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FlywayDataMigrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlywayDataMigrationApplication.class, args);
	}

}
