package com.example.restservice;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;

import org.hibernate.envers.Audited;

@Entity
@EqualsAndHashCode
@Getter
@Table(name="greetings")
@Audited
public class Greeting {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    long id;
    
    @Setter
    @Column(name="content")
    String content;
}
