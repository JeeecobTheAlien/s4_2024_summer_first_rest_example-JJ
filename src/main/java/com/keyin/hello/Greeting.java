package com.keyin.hello;

import jakarta.persistence.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Entity
public class Greeting {

    @Id
    @SequenceGenerator(name = "greeting_sequence", sequenceName = "greeting_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "greeting_sequence")
    private long id;
    private String greeting;
    private String name;

    @ManyToMany
    private List<Language> languages;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    @RestController
    public class FrenchGreetingController {
    @Autowired
    private GreetingService greetingService;

    @PostMapping("french_greeting")
    public ResponseEntity<Greeting> createFrenchGreeting(@RequestBody Greeting newGreeting) {
        Language french = new Language("French");
        newGreeting.setLanguages(List.of(french));
        newGreeting.setGreeting("Bonjour, comment allez-vous?");
        newGreeting.setName("French Greeting");

        Greeting createdGreeting = greetingService.createGreeting(newGreeting);

        return new ResponseEntity<>(createdGreeting, HttpStatus.CREATED);
    }
}
}
