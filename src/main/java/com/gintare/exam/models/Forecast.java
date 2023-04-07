package com.gintare.exam.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
public class Forecast {
    @Id
    @GeneratedValue(strategy = AUTO)
    public Long id;
    public String date;
    public String temperature;
    public String city;
    public String userName;

    public Forecast(String date, String temperature, String city, String userName) {
        this.date = date;
        this.temperature = temperature;
        this.city = city;
        this.userName = userName;
    }

    public Forecast() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
