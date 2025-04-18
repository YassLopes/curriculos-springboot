package com.curriculum.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, String> home() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Welcome to Curriculum API");
        response.put("version", "1.0.0");
        response.put("description", "A REST API for managing curriculum data");
        response.put("endpoints", "/api/persons - Manage person profiles");
        return response;
    }
} 