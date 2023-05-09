package com.example.restservice;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.history.RevisionRepository;

public interface GreetingRepository extends RevisionRepository<Greeting, Long, Long>, CrudRepository<Greeting, Long> { }
