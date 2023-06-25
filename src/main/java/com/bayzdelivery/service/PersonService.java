package com.bayzdelivery.service;

import com.bayzdelivery.model.Person;

import java.util.List;

public interface PersonService {
  public List<Person> getAll();

  public Person save(Person p);

  public Person findById(Long personId);

  public Boolean isUserExist(String email);

}
