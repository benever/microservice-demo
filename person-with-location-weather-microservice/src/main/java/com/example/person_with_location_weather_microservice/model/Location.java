package com.example.person_with_location_weather_microservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Location {
    @Id
    @GeneratedValue
    private int id;
    private String latitude;
    private String longitude;
    private String city;

    @OneToOne(cascade = CascadeType.ALL)
    private Weather weather;

    public Location(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", city='" + city + '\'' +
                ", weather=" + weather +
                '}';
    }


    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }


    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }


    public void setCity(String city) {
        this.city = city;
    }


    public void setWeather(Weather weather) {
        this.weather = weather;
    }
}
