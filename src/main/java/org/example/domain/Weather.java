package org.example.domain;

public class Weather {
    private final String city;
    private final double temperature;

    public Weather(String city, double temperature) {
        this.city = city;
        this.temperature = temperature;
    }

    public String getCity(){
        return city;
    }
    public Double getTemp(){
        return temperature;
    }
}


