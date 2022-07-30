package com.example.restdatajpa.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "laptops")
@ApiModel(description = "Representa un laptop")
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Identificador del laptop")
    private Long id;
    @ApiModelProperty(notes = "Marca del laptop")
    private String manufacturer;
    @ApiModelProperty(notes = "Modelo del laptop")
    private String model;
    @ApiModelProperty(notes = "Procesador del laptop")
    private String processor;
    @ApiModelProperty(notes = "Memoria RAM del laptop")
    private Integer ram;
    @ApiModelProperty(notes = "Precio del laptop")
    private Double price;

    public Laptop() {
    }

    public Laptop(Long id, String manufacturer, String model, String processor, Integer ram, Double price) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.processor = processor;
        this.ram = ram;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "id=" + id +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", processor='" + processor + '\'' +
                ", ram=" + ram +
                ", price=" + price +
                '}';
    }
}
