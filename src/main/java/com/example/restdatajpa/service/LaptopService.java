package com.example.restdatajpa.service;

import com.example.restdatajpa.entities.Laptop;

import java.util.List;
import java.util.Optional;

public interface LaptopService {
    List<Laptop> findAll();
    Laptop findById(Long id);
    Laptop create(Laptop laptop);
    Laptop update(Laptop laptop, Long id);
    void deleteById(Long id);
    void deleteAll();
}
