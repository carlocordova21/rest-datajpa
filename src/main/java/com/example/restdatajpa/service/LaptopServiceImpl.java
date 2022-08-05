package com.example.restdatajpa.service;

import com.example.restdatajpa.controllers.exceptions.NotFoundException;
import com.example.restdatajpa.entities.Laptop;
import com.example.restdatajpa.repositories.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class LaptopServiceImpl implements LaptopService{
    @Autowired
    private LaptopRepository _laptopRepository;

    @Override
    public List<Laptop> findAll() {
        return _laptopRepository.findAll();
    }

    @Override
    public Laptop findById(Long id) {
        return _laptopRepository.findById(id).orElseThrow(() -> new NotFoundException("Laptop not found with id: " + id));
    }

    @Override
    @Transactional
    public Laptop create(Laptop laptop) {
        return _laptopRepository.save(laptop);
    }

    @Override
    public Laptop update(Laptop newLaptop, Long id) {
        Laptop laptop = _laptopRepository.findById(id).orElseThrow(() -> new NotFoundException("Laptop not found with id: " + id));
        laptop.setManufacturer(newLaptop.getManufacturer());
        laptop.setModel(newLaptop.getModel());
        laptop.setProcessor(newLaptop.getProcessor());
        laptop.setRam(newLaptop.getRam());
        laptop.setPrice(newLaptop.getPrice());
        return _laptopRepository.save(laptop);
    }

    @Override
    public void deleteById(Long id) {
        Laptop laptopDeleted = _laptopRepository.findById(id).orElseThrow(() -> new NotFoundException("Laptop not found with id: " + id));
        _laptopRepository.delete(laptopDeleted);
    }

    @Override
    public void deleteAll() {
        _laptopRepository.deleteAll();
    }
}
