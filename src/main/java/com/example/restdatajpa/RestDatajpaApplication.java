package com.example.restdatajpa;

import com.example.restdatajpa.entities.Laptop;
import com.example.restdatajpa.repositories.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class RestDatajpaApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(RestDatajpaApplication.class, args);
		LaptopRepository laptopRepository = context.getBean(LaptopRepository.class);

		//CRUD
		//Store laptop
		Laptop laptop1 = new Laptop(null, "Dell", "XPS 13", "Intel Core i7", 8);
		Laptop laptop2 = new Laptop(null, "HP", "Pavilion", "Intel Core i7", 8);
		laptopRepository.save(laptop1);
		laptopRepository.save(laptop2);

		//Update laptop
		Laptop laptopBuscada = laptopRepository.findById(1L).get();
		laptopBuscada.setProcessor("Intel Core i9");
		laptopBuscada.setRam(12);
		laptopRepository.save(laptopBuscada);
	}
}
