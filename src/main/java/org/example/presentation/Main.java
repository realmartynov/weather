package org.example.presentation;
import org.example.application.WeatherService;
import org.example.domain.InvalidCityNameException;
import org.example.domain.Weather;
import org.example.infrastructure.WeatherRepository;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WeatherRepository weatherRepository = new WeatherRepository();
        WeatherService weatherService = new WeatherService(weatherRepository);

        try (Scanner scan = new Scanner(System.in)) {

            while (true) {
                System.out.print("Введите город (или 'выход' для завершения): ");
                String city = scan.nextLine().trim();

                if (city.equalsIgnoreCase("выход")) {
                    System.out.println("Программа завершена!");
                    break;
                }
                try {
                    Weather weather = weatherService.getWeather(city);
                    System.out.printf("Погода для города %s: %.2f%n", weather.getCity(), weather.getTemp());
                } catch (InvalidCityNameException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
