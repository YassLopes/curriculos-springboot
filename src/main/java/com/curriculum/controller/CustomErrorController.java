package com.curriculum.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<Map<String, String>> handleError() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "404");
        response.put("error", "Not Found");
        response.put("message", "The requested resource was not found");
        response.put("documentation", "Available endpoints: /api/persons");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
} 