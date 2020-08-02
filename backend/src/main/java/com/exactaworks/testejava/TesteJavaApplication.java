package com.exactaworks.testejava;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import com.exactaworks.testejava.model.Spent;
import com.exactaworks.testejava.repository.SpentRepository;

@SpringBootApplication
@EntityScan(basePackageClasses = {TesteJavaApplication.class, Jsr310JpaConverters.class})
public class TesteJavaApplication implements CommandLineRunner{

	@Autowired
	private SpentRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(TesteJavaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Spent spent = new Spent(null, "David Chaves", "bicicleta", Instant.now(), 700.0);
		spent.getTags().addAll(Arrays.asList("diversão de bike", "bike nova"));
		repository.save(spent);
	}

}
