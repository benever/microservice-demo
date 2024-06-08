package com.example.person_microservice.repository;

import com.example.person_microservice.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person,Integer> {
}
