package com.example.location_microservice.service;

import com.example.location_microservice.model.Location;
import com.example.location_microservice.repository.LocationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Optional;

@Service
public class LocationService {
    @Autowired
    private LocationRepository repository;
    @Autowired
    public RestTemplate restTemplate;

    public LocationService(LocationRepository repository) {
        this.repository = repository;
    }

    public Iterable<Location> findAllService() {
        return repository.findAll();
    }

    public Optional<Location> findByIdService(int id) {
        return repository.findById(id);
    }

    public ResponseEntity<Location> saveService(Location location) {
        if(location.getCity() != null){
            setLatLon(location);
        }
        else if(location.getLatitude() != null && location.getLongitude() != null){
            setCity(location);
        }
        return new ResponseEntity<>(repository.save(location),HttpStatus.CREATED);
    }

    public ResponseEntity<Location> saveService(int id,Location location) {
        if(location.getCity() != null){
            setLatLon(location);
        }
        else if(location.getLatitude() != null && location.getLongitude() != null){
            setCity(location);
        }
        restTemplate.postForObject("http://localhost:8081/person/"+id,location.getCity(),Object.class);

        return new ResponseEntity<>(repository.save(location),HttpStatus.CREATED);
    }

    public void deletePersonService(int id) {
        repository.deleteById(id);
    }

    public void setLatLon(Location location){
        String coordLon = "", coordLat = "";
        String city = location.getCity();
        String API_KEY = "0c072284079a302d6d11ad45aaddc75c";
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + city +
                "&appid=" + API_KEY + "&units=imperial";
        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();

            Map<String, Object> respMap = jsonToMap(result.toString());

            Map<String, Object> coord = (Map<String, Object>) respMap.get("coord");
            coordLon = coord.get("lon").toString();
            coordLat = coord.get("lat").toString();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        location.setLongitude(coordLon);
        location.setLatitude(coordLat);
    }

    public void setCity(Location location){
        String API_KEY = "0c072284079a302d6d11ad45aaddc75c";
        String lat = location.getLatitude();
        String lon = location.getLongitude();
        String urlString = "https://api.openweathermap.org/data/2.5/weather?lon=" + lon + "&lat=" + lat
                +"&units=metric&appid=" + API_KEY;
        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();

            Map<String, Object> respMap = jsonToMap(result.toString());

            String name = respMap.get("name").toString();
            System.out.println(name);
            location.setCity(name);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Map<String, Object> jsonToMap(String str) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<Map<String, Object>> typeReference = new TypeReference<>() {
        };
        Map<String, Object> map = objectMapper.readValue(str, typeReference);
        return map;
    }
}
