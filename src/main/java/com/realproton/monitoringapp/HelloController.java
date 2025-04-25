package com.realproton.monitoringapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {

    private final List<byte[]> memoryLeakList = new ArrayList<>();

    @GetMapping("/")
    public String home() {
        return "App is running";
    }

    @GetMapping("/stress")
    public String stressCpu() {
        long endTime = System.currentTimeMillis() + 10000; // 10 seconds
        while (System.currentTimeMillis() < endTime) {
            Math.sqrt(Math.random());
        }
        return "CPU Stress Test Completed!";
    }

    @GetMapping("/leak")
    public String memoryLeak() {
        byte[] chunk = new byte[10 * 1024 * 1024]; // 10MB
        memoryLeakList.add(chunk);
        return "Allocated 10MB! Current chunks: " + memoryLeakList.size();
    }
    
    @GetMapping("/validate")
    public String validateString(@RequestParam String input) {
        // Check if the input is a valid string (e.g., non-empty)
        if (input.matches("[a-zA-Z]+")) {
            return "Valid string: " + input;
        } else {
            // Return an error if input is invalid
            throw new IllegalArgumentException("Invalid input. Only alphabets are allowed.");
        }
    }
}
