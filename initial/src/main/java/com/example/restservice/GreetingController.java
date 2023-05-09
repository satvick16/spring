package com.example.restservice;

import java.util.*;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";

    @Autowired
    private GreetingRepository repository;

    @ApiOperation(value = "Get all previous greetings created")
    @GetMapping("/greeting")
    public List<Greeting> getAllGreetings() {
        List<Greeting> greetings = new ArrayList<>();
        repository.findAll().forEach(greetings::add);
        return greetings;
    }

    @ApiOperation(value = "Create a new greeting")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created a new greeting"),
            @ApiResponse(code = 400, message = "Invalid request parameters")
    })
    @PostMapping("/greeting")
    public Greeting postGreeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        Greeting newGreeting = new Greeting();
        newGreeting.setContent(String.format(template, name));
        repository.save(newGreeting);
        return newGreeting;
    }

}
