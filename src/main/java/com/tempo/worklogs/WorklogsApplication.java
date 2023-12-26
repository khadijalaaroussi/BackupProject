package com.tempo.worklogs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@PropertySource("classpath:application.yml")
public class WorklogsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorklogsApplication.class, args);
	}

}
