package com.example.restdatajpa.service;

import com.example.restdatajpa.entities.Laptop;

public class LaptopPriceCalculator {
    public double calculatePrice(Laptop laptop) {
        if(laptop == null) {
            return -1;
        }
        double price = laptop.getPrice();
        if(laptop.getManufacturer().equals("Dell")) {
            price *= 0.95;
        }
        //Precio de envio
        price += 20;
        return price;
    }
}
