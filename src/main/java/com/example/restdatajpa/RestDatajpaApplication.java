package com.example.restdatajpa;

import com.example.restdatajpa.entities.Laptop;
import com.example.restdatajpa.repositories.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
//@EnableSwagger2
public class RestDatajpaApplication {

	public static void main(String[] args) {
//		SpringApplication.run(RestDatajpaApplication.class, args);
		ApplicationContext context = SpringApplication.run(RestDatajpaApplication.class, args);
		LaptopRepository laptopRepository = context.getBean(LaptopRepository.class);

		//CRUD
		//Store laptop
		Laptop laptop1 = new Laptop(null, "Dell", "XPS 13", "Intel Core i7", 8, 3599.99);
		Laptop laptop2 = new Laptop(null, "HP", "Pavilion", "Intel Core i7", 8, 2999.99);
		laptopRepository.save(laptop1);
		laptopRepository.save(laptop2);
	}
}
