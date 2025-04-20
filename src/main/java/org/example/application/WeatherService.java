package org.example.application;
import org.example.infrastructure.WeatherRepository;
import org.example.domain.InvalidCityNameException;
import org.example.domain.Weather;

public class WeatherService{
    private final WeatherRepository repository;

    public WeatherService(WeatherRepository repository){
        this.repository = repository;
    }
    public Weather getWeather (String city){
        validateCity(city);

        if (repository.exists(city)){
            return repository.findByCity(city);
        }

        double temperature = genTemp();
        Weather weather = new Weather(city, temperature);
        repository.save(weather);
        return weather;
    }

    private void validateCity(String city){
        if (!city.matches("^[А-Яа-яЁё\\-\\s]+$")) {
            throw new InvalidCityNameException();
        }
    }
    private double genTemp(){
        return Math.random() * 200 - 100;
    }

}
