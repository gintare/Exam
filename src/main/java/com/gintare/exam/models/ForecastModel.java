package com.gintare.exam.models;

public class ForecastModel {
    public String date;
    public double temperature;
    public String cityName;
    public String userName;

    public ForecastModel(String date, double temperature) {
        this.date = date;
        this.temperature = temperature;
    }
}
