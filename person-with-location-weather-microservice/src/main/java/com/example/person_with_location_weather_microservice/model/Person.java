package com.example.person_with_location_weather_microservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String city;
    @OneToOne(cascade = CascadeType.ALL)
    private Location location;

    public Person(Person person){
        this.id = person.getId();
        this.name = person.getName();
        this.city = person.getCity();
    }
    public Person(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location=" + location +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
