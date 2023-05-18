package com.example.restservice;

import java.util.*;
import java.util.stream.Collectors;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.history.Revision;

@RestController
@CrossOrigin(origins = "http://localhost:4200/") 
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
    public String postGreeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        Greeting newGreeting = new Greeting();
        newGreeting.setContent(String.format(template, name));
        repository.save(newGreeting);
        return "Greeting successfully created!";
    }

    @ApiOperation(value = "Edit existing greeting")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully edited greeting"),
            @ApiResponse(code = 400, message = "Invalid request parameters")
    })
    @PostMapping("/edit/{id}/{name}")
    public Greeting editGreeting(@PathVariable long id, @PathVariable String name) {
        Greeting target = repository.findById(id).get();
        target.setContent(String.format(template, name));
        repository.save(target);
        return target;
    }

    @ApiOperation(value = "Get the revision history for a given greeting id")
    @GetMapping("/history/{id}")
    public Map<String, String> getGreetingHistory(@PathVariable Long id) {
        Object[] revisionArray = repository.findRevisions(id).getContent().toArray();
        Map<String, String> result = new HashMap<>();

        for (Object element : revisionArray) {
            org.springframework.data.history.Revision revision = (org.springframework.data.history.Revision) element;
            org.springframework.data.envers.repository.support.DefaultRevisionMetadata data = (org.springframework.data.envers.repository.support.DefaultRevisionMetadata) revision.getMetadata();

            String date = data.getRevisionDate().get().toString();
            String type = data.getRevisionType().toString();

            result.put(date, type);
        }

        return result;
    }

}
