package com.example.person_microservice.service;

import com.example.person_microservice.model.Person;
import com.example.person_microservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PersonService {
    @Autowired
    private PersonRepository repository;

    public Iterable<Person> findAll(){
        return repository.findAll();
    }
    public ResponseEntity<Person> findById(@PathVariable int id){
        return new ResponseEntity<>(repository.findById(id).get(), HttpStatus.OK);
    }

    public ResponseEntity<Person> save(@RequestBody Person person){
        return new ResponseEntity<>(repository.save(person),HttpStatus.OK);
    }

    public ResponseEntity<Person> refactor(@PathVariable int id,@RequestBody String city){
        Person newPerson = repository.findById(id).orElse(new Person());
        newPerson.setCity(city);
        HttpStatus status = repository.existsById(id) ? HttpStatus.OK : HttpStatus.CREATED;
        return new ResponseEntity(repository.save(newPerson), status);
    }

    public void deletePerson(@PathVariable int id){
        repository.deleteById(id);
    }
}
