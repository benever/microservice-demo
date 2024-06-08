package com.example.person_with_location_weather_microservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Weather {
    @Id
    @GeneratedValue
    @JsonIgnore
    private int id;
    private double temp;
    private double windSpeed;
    private String main;
    private String description;


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
