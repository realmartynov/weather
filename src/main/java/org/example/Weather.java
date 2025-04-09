package org.example;

import java.util.Scanner;

public class Weather {
    private double x = Math.random()*200 - 100;

    Scanner scan = new Scanner(System.in);
    String city = scan.nextLine();

    public String getTemperature(){
        return "Погода для города " + city + ": " + x;
    }


}

