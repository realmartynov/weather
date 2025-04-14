package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InvalidCityNameException {
        Scanner scan = new Scanner(System.in);
        Map<String, Double> history = new HashMap<>();

        while (true) {
            System.out.print("Введите город (или 'выход' для завершения): ");
            String city = scan.nextLine().trim();

            if (city.equalsIgnoreCase("выход")) {
                System.out.println("Программа завершена!");
                break;
            }

            if (!city.matches("^[А-Яа-яЁё\\-\\s]+$")) {
                throw new InvalidCityNameException();
            }

            String cityKey = city.toLowerCase();

            if (history.containsKey(cityKey)) {
                System.out.printf("Погода для города %s: %.2f%n", city, history.get(cityKey));
            } else {
                Weather weather = new Weather(city);
                double temp = weather.getTemperature();
                history.put(cityKey, temp);
                System.out.printf("Погода для города %s: %.2f%n", city, temp);
            }
        }

        scan.close();
    }
}
