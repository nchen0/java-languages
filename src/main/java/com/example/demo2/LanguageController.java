package com.example.demo2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
// @RestController
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

    @GetMapping("/languages/population")
    public ObjectNode sumPops() {

        List<Language> languages = langrepos.findAll(); // This gives us a list of languages.

        Long total = 0L;
        for (Language l : languages) {
            total = total + l.getPopulation();
        }

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode totalPops = mapper.createObjectNode(); // mapper is going to convert from object to json for us.
        totalPops.put("id", 0);
        totalPops.put("language", "total");
        totalPops.put("population", total);

        LanguageLog message = new LanguageLog("Checked Total Population");
        log.info("Message sent");
        rt.convertAndSend(Demo2Application.QUEUE_NAME, message.toString());
        return totalPops;

    }
}

