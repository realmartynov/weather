package org.example.service.impl;

import org.example.domain.Weather;
import org.example.service.WeatherService;
import org.example.domain.InvalidCityNameException;
import org.example.repository.WeatherRepository;

public class WeatherServiceImpl implements WeatherService {
    private final WeatherRepository repository;

    public WeatherServiceImpl(WeatherRepository repository) {
        this.repository = repository;
    }

    @Override
    public Weather getWeather (String city){
        validateCity(city);

        if (repository.exists(city))
            return repository.findByCity(city);
        double temperature = genTemp();
        Weather weather = new Weather(city, temperature);
        repository.save(weather);
        return weather;
    }
    @Override
    public void validateCity(String city){
        if (!city.matches("^[А-Яа-яЁё\\-\\s]+$"))
            throw new InvalidCityNameException();
    }
    @Override
    public double genTemp(){
        return Math.random() * 200 - 100;
    }
}
