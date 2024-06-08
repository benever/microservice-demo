package com.example.person_with_location_weather_microservice.controller;

import com.example.person_with_location_weather_microservice.model.Location;
import com.example.person_with_location_weather_microservice.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/person-loc")
public class PersonWithLocationWeather {
    @Autowired
    public RestTemplate restTemplate;

    @GetMapping("/{personId}")
    public ResponseEntity<Person> person(@PathVariable int personId){
        Person person = restTemplate.getForObject("http://localhost:8081/person/" + personId, Person.class);
        assert person != null;
        Location location = restTemplate.postForObject("http://localhost:8082/location",new Location(person.getCity()),Location.class);
        person.setLocation(location);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }
}
