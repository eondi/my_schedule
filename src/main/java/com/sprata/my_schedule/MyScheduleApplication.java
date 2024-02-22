package com.sprata.my_schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class MyScheduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyScheduleApplication.class, args);
	}

}
