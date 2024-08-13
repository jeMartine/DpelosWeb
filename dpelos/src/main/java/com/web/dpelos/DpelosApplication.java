package com.web.dpelos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.web.dpelos.entity.Mascota;

@SpringBootApplication
@Configuration //Beans
@EnableAutoConfiguration //Activar auto configuracion
@ComponentScan //Escanear la info.
public class DpelosApplication {

	public static void main(String[] args) {
		SpringApplication.run(DpelosApplication.class, args);
	}
}
