package com.example.person_microservice.controller;

import com.example.person_microservice.model.Person;
import com.example.person_microservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonRepository repository;

    @GetMapping
    public Iterable<Person> findAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable int id){
        return new ResponseEntity<>(repository.findById(id).get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Person> save(@RequestBody Person person){
        return new ResponseEntity<>(repository.save(person),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable int id){
        repository.deleteById(id);
    }
}
