package com.example.restdatajpa.controllers;

import com.example.restdatajpa.entities.Laptop;
import com.example.restdatajpa.repositories.LaptopRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {
    private final LaptopRepository _laptopRepository;
    private final Logger _logger = LoggerFactory.getLogger(LaptopController.class);

    public LaptopController(LaptopRepository _laptopRepository) {
        this._laptopRepository = _laptopRepository;
    }

    /**
     * /api/laptops
     * @return List<Laptop>
     */
    @GetMapping("/api/laptops")
    @ApiOperation(value = "Lista todos los laptops", response = Laptop.class, responseContainer = "List")
    public List<Laptop> findAll() {
        //Recuperar todas las laptops de la base de datos
        return _laptopRepository.findAll();
    }

    /**
     * /api/laptops/{id}
     * @param id Long
     * @return ResponseEntity<Laptop>
     */
    @GetMapping("/api/laptops/{id}")
    @ApiOperation(value = "Busca un laptop por id", response = Laptop.class)
    public ResponseEntity<Laptop> findById(@PathVariable Long id) {
        //Recuperar una laptop por su id
        Optional<Laptop> optLaptop = _laptopRepository.findById(id);
        if (optLaptop.isEmpty()){
            _logger.warn("Laptop con id {} no encontrado", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optLaptop.get());
    }

    /**
     * /api/laptops
     * @param laptop Laptop
     * @return ResponseEntity<Laptop>
     */
    @PostMapping("/api/laptops")
    @ApiOperation("Crea un nuevo laptop")
    @ApiResponse(code = 400, message = "Bad Request")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop) {
        if(laptop.getId() != null) {
            _logger.warn("Trying to create a laptop with an id");
            return ResponseEntity.badRequest().build();
        }
        //Crear una laptop
        _logger.info("Creating laptop: {}", laptop);
        Laptop laptopCreado = _laptopRepository.save(laptop);
        URI location = URI.create("/api/laptops/" + laptopCreado.getId());
        return ResponseEntity.created(location).body(laptopCreado);
    }

    /**
     *  /api/laptops/{id}
     * @param newLaptop Laptop
     * @param id Long
     * @return ResponseEntity<Laptop>
     */
    @PutMapping("api/laptops/{id}")
    @ApiOperation("Actualiza un laptop")
    @ApiResponse(code = 400, message = "Bad Request")
    public ResponseEntity<Laptop> update(@RequestBody Laptop newLaptop, @PathVariable Long id) {
        if(newLaptop.getId() != null && !newLaptop.getId().equals(id)) {
            _logger.warn("Trying to update a laptop with an id different from the one in the path");
            return ResponseEntity.badRequest().build();
        }
        Optional<Laptop> optLaptop = _laptopRepository.findById(id);
        if (optLaptop.isEmpty()) {
            _logger.warn("Trying to update a laptop that does not exist");
            return ResponseEntity.notFound().build();
        }
        //Actualizar una laptop
        Laptop laptopActualizado = optLaptop.get();
        laptopActualizado.setManufacturer(newLaptop.getManufacturer());
        laptopActualizado.setModel(newLaptop.getModel());
        laptopActualizado.setProcessor(newLaptop.getProcessor());
        laptopActualizado.setRam(newLaptop.getRam());
        laptopActualizado.setPrice(newLaptop.getPrice());

        _logger.info("Updating laptop with id: " + laptopActualizado.getId());
        _laptopRepository.save(laptopActualizado);
        return ResponseEntity.ok(laptopActualizado);
    }

    /**
     * /api/laptops/{id}
     * @param id Long
     * @return ResponseEntity<Laptop>
     */
    @DeleteMapping("/api/laptops/{id}")
    @ApiOperation("Elimina un laptop")
    @ApiResponse(code = 204, message = "No Content")
    public ResponseEntity<Laptop> delete(@PathVariable Long id) {
        //Eliminar una laptop
        Optional<Laptop> optLaptop = _laptopRepository.findById(id);
        if(optLaptop.isEmpty()) {
            _logger.warn("Trying to delete a laptop that does not exist");
            return ResponseEntity.notFound().build();
        }
        _logger.info("Deleting laptop with id: " + id);
        _laptopRepository.delete(optLaptop.get());
        return ResponseEntity.noContent().build();
    }

    /**
     * /api/laptops/{id}/delete
     * @return ResponseEntity<Laptop>
     */
    @DeleteMapping("/api/laptops")
    @ApiOperation("Elimina todos los laptops")
    @ApiResponse(code = 204, message = "No Content")
    public ResponseEntity<Laptop> deleteAll() {
        //Eliminar todas las laptops
        _logger.info("Deleting all laptops");
        _laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
