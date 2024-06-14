package com.example.person_microservice.controller;

import com.example.person_microservice.model.Person;
import com.example.person_microservice.repository.PersonRepository;
import com.example.person_microservice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService service;

    @GetMapping
    public Iterable<Person> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable int id){
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<Person> save(@RequestBody Person person){
        return service.save(person);
    }
    @PostMapping("/{id}")
    public ResponseEntity<Person> refactor(@PathVariable int id,@RequestBody String city){
        return service.refactor(id,city);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable int id){
        service.deletePerson(id);
    }
}
