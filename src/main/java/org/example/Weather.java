package org.example;

public class Weather {
    private String city;
    private double x;

    public Weather(String city){
        this.city = city;
        this.x = Math.random() * 200 - 100;
    }

    public String getTemperature(){
        return "Погода для города " + city + ": " + x;
    }


}

