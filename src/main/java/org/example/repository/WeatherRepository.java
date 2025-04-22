package org.example.repository;
import org.example.domain.Weather;

import java.util.HashMap;
import java.util.Map;

public class WeatherRepository {
    private final Map<String, Weather> storage = new HashMap<>();

    public Weather findByCity(String city){
        return storage.get(city.toLowerCase());

    }
    public void save(Weather weather){
        storage.put(weather.getCity().toLowerCase(), weather);

    }
    public boolean exists(String city){
        return storage.containsKey(city.toLowerCase());
    }
}
