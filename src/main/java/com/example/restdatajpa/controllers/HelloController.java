package com.example.restdatajpa.controllers;

//import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
//    @Value("${app.username}")
//    private String username;
    @GetMapping("/api/hello")
    public String hello() {
//        System.out.println(this.username);
        return "Hello World";
    }
}
