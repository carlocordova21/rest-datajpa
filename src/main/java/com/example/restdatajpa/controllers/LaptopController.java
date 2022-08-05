package com.example.restdatajpa.controllers;

import com.example.restdatajpa.controllers.exceptions.RequestIdIsNotNullException;
import com.example.restdatajpa.entities.Laptop;
import com.example.restdatajpa.service.LaptopService;
import com.example.restdatajpa.service.LaptopServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/laptops")
public class LaptopController {
    @Autowired
    private LaptopService _laptopService;
    private final Logger _logger = LoggerFactory.getLogger(LaptopController.class);

    /**
     * /api/laptops
     * @return List<Laptop>
     */
    @GetMapping
    @ApiOperation(value = "Lista todos los laptops", response = Laptop.class, responseContainer = "List")
    public List<Laptop> findAll() {
        //Recuperar todas las laptops de la base de datos
        return _laptopService.findAll();
    }

    /**
     * /api/laptops/{id}
     * @param id Long
     * @return ResponseEntity<Laptop>
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "Busca un laptop por id", response = Laptop.class)
    public ResponseEntity<Laptop> findById(@PathVariable Long id) {
        //Recuperar una laptop por su id
        Laptop laptop = _laptopService.findById(id);
        return ResponseEntity.ok(laptop);
    }

    /**
     * /api/laptops
     * @param laptop Laptop
     * @return ResponseEntity<Laptop>
     */
    @PostMapping
    @Transactional
    @ApiOperation("Crea un nuevo laptop")
    @ApiResponse(code = 400, message = "Bad Request")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop) {
        if(laptop.getId() != null) {
            String errorMessage = "Trying to create a laptop with an id " + laptop.getId();
            _logger.warn(errorMessage);
            throw new RequestIdIsNotNullException(errorMessage);
        }
        //Crear una laptop
        _logger.info("Creating laptop: {}", laptop);
        Laptop newLaptop = _laptopService.create(laptop);
        URI location = URI.create("/api/laptops/" + newLaptop.getId());
        return ResponseEntity.created(location).body(newLaptop);
    }

    /**
     *  /api/laptops/{id}
     * @param newLaptop Laptop
     * @param id Long
     * @return ResponseEntity<Laptop>
     */
    @PutMapping("/{id}")
    @Transactional
    @ApiOperation("Actualiza un laptop")
    @ApiResponse(code = 400, message = "Bad Request")
    public ResponseEntity<Laptop> update(@RequestBody Laptop newLaptop, @PathVariable Long id) {
        if(newLaptop.getId() != null) {
            _logger.warn("Trying to update a laptop with an id different from the one in the path");
            throw new RequestIdIsNotNullException("Id is not null " + newLaptop.getId());
        }
        Laptop laptopActualizado = _laptopService.update(newLaptop, id);
        //Actualizar una laptop
        _logger.info("Updating laptop with id: " + laptopActualizado.getId());
        return ResponseEntity.ok(laptopActualizado);
    }

    /**
     * /api/laptops/{id}
     * @param id Long
     * @return ResponseEntity<Laptop>
     */
    @DeleteMapping("/{id}")
    @Transactional
    @ApiOperation("Elimina un laptop")
    @ApiResponse(code = 204, message = "No Content")
    public ResponseEntity<Laptop> delete(@PathVariable Long id) {
        //Eliminar una laptop
        _logger.info("Deleting laptop with id: " + id);
        _laptopService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * /api/laptops/{id}/delete
     * @return ResponseEntity<Laptop>
     */
    @DeleteMapping
    @Transactional
    @ApiOperation("Elimina todos los laptops")
    @ApiResponse(code = 204, message = "No Content")
    public ResponseEntity<Laptop> deleteAll() {
        //Eliminar todas las laptops
        _logger.info("Deleting all laptops");
        _laptopService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
