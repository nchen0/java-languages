package com.example.demo2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class LanguageController {
    private final LanguageRepository langrepos;
    private final RabbitTemplate rt;

    public LanguageController(LanguageRepository langrepos, RabbitTemplate rt) {
        this.langrepos = langrepos;
        this.rt = rt;
    }

    @GetMapping("/languages")
    public List<Language> all() {
        return langrepos.findAll(); // returns all of the languages that is in our database table and will return it, because we have jackson installed, it will return it in a json format.
    }

    @GetMapping("/languages/{id}")
    public Language findOne(@PathVariable Long id) {
        return langrepos.findById(id)
                .orElseThrow(() -> new LanguageNotFoundException(id));
    }

    @PostMapping("/languages")
    public List<Language> newLanguage(@RequestBody List<Language> newLanguages) {
        return langrepos.saveAll(newLanguages);
    }
}

