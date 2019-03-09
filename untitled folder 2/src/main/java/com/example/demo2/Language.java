package com.example.demo2;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Language {
    // This will be our Java objects representation of our data.
    private @Id @GeneratedValue Long id;
    private String language;
    private Long population;

    public Language() {
        // need default constructor for jpa
    }

    public Language(String language, Long population) {
        this.language = language;
        this.population = population;
    }


}
