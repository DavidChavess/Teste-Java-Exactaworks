package com.exactaworks.testejava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(basePackageClasses = {TesteJavaApplication.class, Jsr310JpaConverters.class})
public class TesteJavaApplication{
	
	public static void main(String[] args) {
		SpringApplication.run(TesteJavaApplication.class, args);
	}
}
