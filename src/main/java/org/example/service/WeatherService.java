package org.example.service;
import org.example.domain.Weather;

public interface WeatherService {
    Weather getWeather(String city);
    void validateCity(String city);
    double genTemp();
}
