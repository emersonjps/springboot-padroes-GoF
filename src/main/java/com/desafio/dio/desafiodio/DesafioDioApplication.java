package com.desafio.dio.desafiodio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;

@EnableFeignClients
@OpenAPIDefinition(servers = {@Server(url = "/", description = "Default Server URL")})
@SpringBootApplication
public class DesafioDioApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioDioApplication.class, args);
	}

}
