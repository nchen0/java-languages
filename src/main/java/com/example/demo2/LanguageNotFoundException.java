package com.example.demo2;

public class LanguageNotFoundException extends RuntimeException {
    public LanguageNotFoundException (Long id) {
        super("Could not find language");
    }
}
