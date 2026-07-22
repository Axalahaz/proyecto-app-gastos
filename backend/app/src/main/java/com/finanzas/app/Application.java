package com.finanzas.app;

import org.springframework.boot.SpringApplication;

// permite seleccionar los packages a leer
import org.springframework.boot.autoconfigure.SpringBootApplication;

// permite seleccionar los repositories a leer
// import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	

}
