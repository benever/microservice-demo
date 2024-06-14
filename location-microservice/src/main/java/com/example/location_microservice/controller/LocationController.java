package com.example.location_microservice.controller;

import com.example.location_microservice.model.Location;
import com.example.location_microservice.repository.LocationRepository;
import com.example.location_microservice.service.LocationService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/location")
@NoArgsConstructor
public class LocationController {
    @Autowired
    private LocationService service;


    public LocationController(LocationService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<Location> findAll() {
        return service.findAllService();
    }

    @GetMapping("/{id}")
    public Optional<Location> findById(@PathVariable int id) {
        return service.findByIdService(id);
    }

    @PostMapping
    public ResponseEntity<Location> save(@RequestBody Location location) {
        return service.saveService(location);
    }
    @PostMapping("/{id}")
    public ResponseEntity<Location> saveOnPerson(@PathVariable int id, @RequestBody Location location) {
        return service.saveService(id,location);
    }

    @DeleteMapping("/{id}")
    public void deleteLocation(@PathVariable int id){
        service.deletePersonService(id);
    }
}
