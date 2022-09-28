package com.lucasdev3.crudbasicospring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CrudbasicospringApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CrudbasicospringApplication.class, args);
	}
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public void run(String... args) throws Exception {

	}

}
