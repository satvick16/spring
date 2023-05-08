package com.example.restservice;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    GreetingRepository repository = new GreetingRepository();

    @ApiOperation(value = "Get all previous greetings created")
    @GetMapping("/greeting")
    public List<Greeting> getAllGreetings() {
        return repository.findAll();
    }

    @ApiOperation(value = "Create a new greeting")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created a new greeting"),
            @ApiResponse(code = 400, message = "Invalid request parameters")
    })
    @PostMapping("/greeting")
    public Greeting postGreeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        Greeting newGreeting = new Greeting(String.format(template, name));
        repository.save(newGreeting);
        return newGreeting;
    }

}
