package com.stg.makeathon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.stg.makeathon.services.EventService;
import com.stg.makeathon.services.EventServiceImpl;

@SpringBootApplication
public class TechadvApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechadvApplication.class, args);
	}
	
	@Bean
	public EventService eventService() {
		return new EventServiceImpl();
	}
}
