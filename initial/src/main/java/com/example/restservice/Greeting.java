package com.example.restservice;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@EqualsAndHashCode
@NoArgsConstructor
public class Greeting {
    @Id @Getter @Setter
    @GeneratedValue(strategy=GenerationType.AUTO)
    long id;
    
    @Getter @Setter
    String content;
}
