package com.bayzdelivery.controller;

import com.bayzdelivery.model.Person;
import com.bayzdelivery.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

  @Autowired
  PersonService personService;

  @PostMapping
  public ResponseEntity<Person> register(@RequestBody Person p) {
    p.setType(Character.toUpperCase(p.getType()));
    return ResponseEntity.ok(personService.save(p));
  }

  @GetMapping
  public ResponseEntity<List<Person>> getAllPersons() {
    return ResponseEntity.ok(personService.getAll());
  }

  @GetMapping(path = "/{person-id}")
  public ResponseEntity<Person> getPersonById(@PathVariable(name="person-id", required=true)Long personId) {
    return ResponseEntity.ok(personService.findById(personId));
  }

}
