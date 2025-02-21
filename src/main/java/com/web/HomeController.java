package com.web;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.*;

@RestController
@RequestMapping("/bfhl")  // This maps all endpoints under "/bfhl"
public class HomeController {

    @GetMapping
    public ResponseEntity<Map<String, Integer>> getOperationCode() {
        Map<String, Integer> response = new HashMap<>();
        response.put("operation_code", 1);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> processInput(@RequestBody Map<String, List<String>> requestBody) {
        List<String> data = requestBody.get("data");

        if (data == null || data.isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("is_success", false));
        }

        List<String> numbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        String highestAlphabet = "";

        for (String item : data) {
            if (item.matches("\\d+")) {
                numbers.add(item);
            } else if (item.matches("[a-zA-Z]")) {
                alphabets.add(item);
                if (highestAlphabet.isEmpty() || item.compareToIgnoreCase(highestAlphabet) > 0) {
                    highestAlphabet = item;
                }
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("is_success", true);
        response.put("user_id", "john_doe_17091999"); // Replace with dynamic values if needed
        response.put("email", "john@xyz.com");
        response.put("roll_number", "ABCD123");
        response.put("numbers", numbers);
        response.put("alphabets", alphabets);
        response.put("highest_alphabet", highestAlphabet.isEmpty() ? new ArrayList<>() : List.of(highestAlphabet));

        return ResponseEntity.ok(response);
    }

    // ✅ New Endpoint for Root ("/") to prevent 404 errors
    @GetMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Welcome to the Bajaj API! Use /bfhl for operations.");
    }
}
