package com.weather.api;

public class WeatherResponse {
    private String condition;
    private double wind_speed;
    private int humidity;
    private double temp;

    // Constructors, getters, and setters

    public WeatherResponse(String condition, double wind_speed, int humidity, double temp) {
        this.condition = condition;
        this.wind_speed = wind_speed;
        this.humidity = humidity;
        this.temp = temp;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public double getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(double wind_speed) {
        this.wind_speed = wind_speed;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }
}
