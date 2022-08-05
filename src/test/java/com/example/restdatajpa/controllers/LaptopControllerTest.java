package com.example.restdatajpa.controllers;

import com.example.restdatajpa.config.WebSecurityConfig;
import com.example.restdatajpa.entities.Laptop;
import com.example.restdatajpa.repositories.LaptopRepository;
import com.example.restdatajpa.service.LaptopService;
import com.example.restdatajpa.service.LaptopServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = WebSecurityConfig.class)
@WebMvcTest(LaptopController.class)
//@WebAppConfiguration
//@SpringBootTest
//@AutoConfigureMockMvc
class LaptopControllerTest {
    @Autowired
    private WebApplicationContext _context;
    private MockMvc _mockMvc;

    @Mock
    private LaptopRepository _laptopRepository;
    @InjectMocks
    private LaptopServiceImpl _laptopServiceImpl;

    @BeforeEach
    private void setUp() {
        MockitoAnnotations.openMocks(this);
        this._mockMvc = MockMvcBuilders
                .webAppContextSetup(_context)
                .apply(springSecurity())
                .build();
    }

    @DisplayName("Lista todos los laptops")
    @Test
    void findAll() throws Exception {
        List<Laptop> laptops = new ArrayList<>();
        Laptop laptop = new Laptop("Lenovo", "IdeaPad 3", "Intel i7", 4, 1999.99);
        laptops.add(laptop);
        when(_laptopRepository.findAll()).thenReturn(List.of(laptop));

        _mockMvc.perform(get("/api/laptops"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//                .andExpect(jsonPath("$", Matchers.hasSize(1)));

        verify(_laptopRepository, times(1)).findAll();
    }

    @Test
    @WithMockUser(roles = "USER")
    void findById() throws Exception {
//        when(_laptopRepository.findById(1L)).thenReturn(new Laptop("Lenovo", "IdeaPad 3", "Intel i7", 4, 1999.99));
//
//        _mockMvc.perform(get("/api/laptops/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//
//        verify(_laptopRepository, times(1)).findById(1L);
    }

    @Test
    void create() {
        Laptop laptop = new Laptop();

    }

    @Test
    void update() {


    }

    @Test
    void delete() {

    }

    @Test
    void deleteAll() {

    }
}