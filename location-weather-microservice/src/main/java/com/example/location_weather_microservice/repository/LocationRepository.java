package com.example.location_weather_microservice.repository;

import com.example.location_weather_microservice.model.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<Location,Integer> {
}
