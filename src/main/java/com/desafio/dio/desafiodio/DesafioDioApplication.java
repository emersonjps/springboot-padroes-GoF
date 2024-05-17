package com.desafio.dio.desafiodio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class DesafioDioApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioDioApplication.class, args);
	}

}
