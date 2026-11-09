package com.upiiz.entrenadores;

import org.springdoc.core.configuration.SpringDocHateoasConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Aquí le decimos que ignore la configuración que está causando el conflicto
@SpringBootApplication(exclude = {SpringDocHateoasConfiguration.class})
public class EntrenadoresApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntrenadoresApplication.class, args);
	}
}