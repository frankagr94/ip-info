package com.mercadolibre.ipinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IpInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(IpInfoApplication.class, args);
	}

}
