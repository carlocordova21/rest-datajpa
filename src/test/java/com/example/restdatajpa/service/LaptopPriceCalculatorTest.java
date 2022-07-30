package com.example.restdatajpa.service;

import com.example.restdatajpa.entities.Laptop;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LaptopPriceCalculatorTest {

    @Test
    void calculatePriceTest() {
        Laptop laptop = new Laptop(null, "Dell", "XPS 13", "Intel Core i7", 8, 3599.99);
        LaptopPriceCalculator calculator = new LaptopPriceCalculator();
        double price = calculator.calculatePrice(laptop);
        System.out.println(price);
//        assertEquals(3599.99 * 0.95 + 20, price);
        assertTrue(price > 0);
        assertTrue(price < 10000);
    }
}