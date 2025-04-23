package org.example.repository;

import org.example.DBUtil;
import org.example.domain.Weather;

import java.sql.*;
import java.time.LocalDateTime;

public class WeatherRepository {
    //private final Map<String, Weather> storage = new HashMap<>();

    public Weather findByCity(String city){
        //return storage.get(city.toLowerCase());\
        String sql = "SELECT city, temperature, created_at, updated_at, last_access_datetime FROM weather WHERE LOWER(city) = LOWER(?)";

        return null;
    }
    public void save(Weather weather){
        //storage.put(weather.getCity().toLowerCase(), weather);
        if (exists(weather.getCity())) {
            update(weather);
        } else {
            insert(weather);
        }
    }

    private void insert(Weather weather) {

    }

    private void update(Weather weather) {

    }

    public boolean exists(String city){
        //return storage.containsKey(city.toLowerCase());
        return false;
    }
    
}
