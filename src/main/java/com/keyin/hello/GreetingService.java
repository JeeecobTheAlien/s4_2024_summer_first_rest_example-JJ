package com.keyin.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class GreetingService {
    @Autowired
    private GreetingRepository greetingRepository;

    @Autowired
    private LanguageRepository languageRepository;

    public List<Greeting> findGreetingsByNameAndGreeting(String name, String greeting) {
        return greetingRepository.findByNameAndGreeting(name, greeting);
    }

    public List<Greeting> getAllGreetings() {
        return (List<Greeting>) greetingRepository.findAll();
    }

    public Greeting getGreeting(Integer index) {
        return getGreeting(index.longValue());
    }

    public Greeting createGreeting(Greeting newGreeting) {
        if (newGreeting.getLanguages() == null) {
            Language english = languageRepository.findByName("English");
            Language french = languageRepository.findByName("French");

            if (english == null) {
                english = new Language();
                languageRepository.save(english);
            }

            if (french == null) {
                french = new Language("French");
                french = languageRepository.save(french);
            }

            ArrayList<Language> languageArrayList = new ArrayList<Language>();
            languageArrayList.add(english);
            languageArrayList.add(french);

            newGreeting.setLanguages(languageArrayList);
        } else {
            for (Language language : newGreeting.getLanguages()) {
                Language langInDB = languageRepository.findByName(language.getName());

                if (langInDB == null) {
                    language = languageRepository.save(language);
                }
            }
        }

        return greetingRepository.save(newGreeting);
    }

    public Greeting updateGreeting(Integer index, Greeting updatedGreeting) {
        Greeting greetingToUpdate = getGreeting(index);

        greetingToUpdate.setName(updatedGreeting.getName());
        greetingToUpdate.setGreeting(updatedGreeting.getGreeting());
        greetingToUpdate.setLanguages(updatedGreeting.getLanguages());

        return greetingRepository.save(greetingToUpdate);
    }

    public void deleteGreeting(Integer index) {
        greetingRepository.delete(getGreeting(index.longValue()));
    }

    public List<Greeting> getAllGreetingsByLanguage(String language) {
        return greetingRepository.findByLanguages_LanguageName(language);
    }

    private Greeting getGreeting(long index) {
        Optional<Greeting> result = greetingRepository.findById(index);

        if (result.isPresent()) {
            return result.get();
        }

        return null;
    }
}