package com.example.restdatajpa.controllers;

import com.example.restdatajpa.entities.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {
    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @DisplayName("Lista todos los laptops")
    @Test
    void findAll() {
        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/api/laptops", Laptop[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());

        List<Laptop> laptops = Arrays.asList(Objects.requireNonNull(response.getBody()));
        assertEquals(0, laptops.size());
    }

    @Test
    void findById() {
        final String url = String.format("/api/laptops/%d", 1);
        ResponseEntity<Laptop> response = testRestTemplate.getForEntity(url, Laptop.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void create() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        String json = "{\r\n    \"manufacturer\": \"Lenovo\",\r\n    \"model\": \"Ideapad 3\",\r\n    \"processor\": \"AMD Ryzen 3\",\r\n    \"ram\": 8\r\n}";
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Laptop> response = testRestTemplate.postForEntity("/api/laptops", request, Laptop.class);

        Laptop result = response.getBody();
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assert result != null;
        assertEquals(1L, result.getId());
    }

    @Test
    void update() {
        int id = 1;
        final String url = String.format("/api/laptops/%d", id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        String json = "{\r\n    \"id\": 1,\r\n    \"manufacturer\": \"Lenovo\",\r\n    \"model\": \"Ideapad 3\",\r\n    \"processor\": \"AMD Ryzen 3\",\r\n    \"ram\": 8\r\n}";
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(id));

        ResponseEntity<Laptop> responseFind = testRestTemplate.getForEntity(url, Laptop.class, params);
        if (responseFind.getStatusCode() == HttpStatus.OK) {
            ResponseEntity<Laptop> response = testRestTemplate.exchange(url, HttpMethod.PUT, request, Laptop.class, params);
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(200, response.getStatusCodeValue());
        } else {
            assertEquals(HttpStatus.NOT_FOUND, responseFind.getStatusCode());
            assertEquals(404, responseFind.getStatusCodeValue());
        }

    }

    @Test
    void delete() {
        int id = 1;
        final String url = String.format("/api/laptops/%d", id);

        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(id));
        ResponseEntity<Laptop> responseFind = testRestTemplate.getForEntity(url, Laptop.class, params);
        if (responseFind.getStatusCode() == HttpStatus.OK) {
            ResponseEntity<Laptop> response = testRestTemplate.exchange(url, HttpMethod.DELETE, null, Laptop.class, params);
            assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
            assertEquals(204, response.getStatusCodeValue());
        } else {
            assertEquals(HttpStatus.NOT_FOUND, responseFind.getStatusCode());
            assertEquals(404, responseFind.getStatusCodeValue());
        }
    }

    @Test
    void deleteAll() {
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops/", HttpMethod.DELETE, null, Laptop.class);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}