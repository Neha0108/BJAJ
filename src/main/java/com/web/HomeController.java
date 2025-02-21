package com.web;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.*;

@RestController
@RequestMapping("/bfhl")
public class HomeController {

    @GetMapping
    public ResponseEntity<Map<String, Integer>> getOperationCode() {
        Map<String, Integer> response = new HashMap<>();
        response.put("operation_code", 1);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> processInput(@RequestBody Map<String, List<String>> requestBody) {
        // Validate request body
        if (requestBody == null || !requestBody.containsKey("data")) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Invalid input format"));
        }

        List<String> data = requestBody.get("data");

        // Check if data is empty
        if (data == null || data.isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("is_success", false));
        }

        List<String> numbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();

        // Process input
        for (String item : data) {
            if (item.matches("\\d+")) {
                numbers.add(item);
            } else if (item.matches("[a-zA-Z]")) {
                alphabets.add(item);
            }
        }

        // Determine highest alphabet (case insensitive)
        String highestAlphabet = alphabets.isEmpty() ? "" : Collections.max(alphabets, String.CASE_INSENSITIVE_ORDER);

        // Construct response
        Map<String, Object> response = new HashMap<>();
        response.put("is_success", true);
        response.put("user_id", generateUserId("John Doe", "17091999"));  // Replace with dynamic values
        response.put("email", "john@xyz.com");
        response.put("roll_number", "ABCD123");
        response.put("numbers", numbers);
        response.put("alphabets", alphabets);
        response.put("highest_alphabet", highestAlphabet.isEmpty() ? new ArrayList<>() : List.of(highestAlphabet));

        return ResponseEntity.ok(response);
    }

    // Helper method to generate dynamic user_id
    private String generateUserId(String fullName, String dob) {
        return fullName.toLowerCase().replace(" ", "_") + "_" + dob;
    }
}
