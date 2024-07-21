package com.keyin.hello;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class GreetingController {
    @Autowired
    private GreetingService greetingService;

    @GetMapping("search_greeting")
    public List<Greeting> searchGreeting(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "greeting", required = false) String greeting) {
        return greetingService.findGreetingsByNameAndGreeting(name, greeting);
    }

    @GetMapping("greetings")
    public List<Greeting> getAllGreetings() {
        return greetingService.getAllGreetings();
    }

    @GetMapping("greeting/{index}")
    public Greeting getGreeting(@PathVariable Integer index) {
        return greetingService.getGreeting(index);
    }

    @PostMapping("greeting")
    public Greeting createGreeting(@RequestBody Greeting newGreeting) {
        return greetingService.createGreeting(newGreeting);
    }

    @PutMapping("greeting/{index}")
    public Greeting updateGreeting(@PathVariable Integer index, @RequestBody Greeting updatedGreeting) {
        return greetingService.updateGreeting(index, updatedGreeting);
    }

    @DeleteMapping("greeting/{index}")
    public void deleteGreeting(@PathVariable Integer index) {
        greetingService.deleteGreeting(index);
    }
}

@RestController
@CrossOrigin
@RequestMapping("/french")
class FrenchGreetingController {
    @Autowired
    private GreetingService greetingService;

    @GetMapping("search_french_greeting")
    public List<Greeting> searchFrenchGreeting(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "greeting", required = false) String greeting) {
        return greetingService.findGreetingsByNameAndGreeting(name, greeting);
    }

    @GetMapping("french_greetings")
    public List<Greeting> getAllFrenchGreetings() {
        return greetingService.getAllGreetings("French");
    }

    @GetMapping("french_greeting/{index}")
    public Greeting getFrenchGreeting(@PathVariable Integer index) {
        return greetingService.getGreeting(index);
    }

    @PostMapping("french_greeting")
    public Greeting createFrenchGreeting(@RequestBody Greeting newGreeting) {
        Language french = new Language("French");
        newGreeting.setLanguages(List.of(french));
        return greetingService.createGreeting(newGreeting);
    }

    @PutMapping("french_greeting/{index}")
    public Greeting updateFrenchGreeting(@PathVariable Integer index, @RequestBody Greeting updatedGreeting) {
        return greetingService.updateGreeting(index, updatedGreeting);
    }

    @DeleteMapping("french_greeting/{index}")
    public void deleteFrenchGreeting(@PathVariable Integer index) {
        greetingService.deleteGreeting(index);
    }
}