package com.example.location_weather_microservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Weather {
    @Id
    @GeneratedValue
    @JsonIgnore
    private int id;
    private double temp;
    private double windSpeed;
    private String main;
    private String description;

    public Weather() {
    }

    public Weather(double temp, double windSpeed, String main, String description) {
        this.temp = temp;
        this.windSpeed = windSpeed;
        this.main = main;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "temp=" + temp +
                ", windSpeed=" + windSpeed +
                ", main='" + main + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
