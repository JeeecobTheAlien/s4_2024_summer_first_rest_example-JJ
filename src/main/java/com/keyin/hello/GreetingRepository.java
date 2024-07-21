package com.keyin.hello;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GreetingRepository extends JpaRepository<Greeting, Long> {
    public List<Greeting> findByNameAndGreeting(String name, String greeting);
    List<Greeting> findByLanguages_LanguageName(String languageName);
}