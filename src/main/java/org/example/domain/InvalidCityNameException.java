package org.example.domain;

public class InvalidCityNameException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Название должно быть на русском!";
    }
}
