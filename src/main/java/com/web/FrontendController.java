package com.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Controller
public class FrontendController {
    private final String API_URL = "http://localhost:8080/bfhl";

    @GetMapping("/")
    public String showForm() {
        return "index"; // Renders index.html
    }

    @PostMapping("/process")
    public String processInput(@RequestParam String jsonInput, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        
        // Convert input JSON string to Map
        Map<String, Object> request = Map.of("data", Arrays.asList(jsonInput.replaceAll("[{}\"]", "").split(",")));

        // Send request to backend API
        Map<String, Object> response = restTemplate.postForObject(API_URL, request, Map.class);

        // Add response data to model
        model.addAttribute("response", response);
        return "index";
    }
}
