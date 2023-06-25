package com.bayzdelivery.service;

import com.bayzdelivery.exceptions.ApiRequestException;
import com.bayzdelivery.exceptions.NotFoundException;
import com.bayzdelivery.model.Person;
import com.bayzdelivery.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService{

  private static final Logger LOG = LoggerFactory.getLogger(PersonServiceImpl.class);

  @Autowired
  PersonRepository personRepository;

  @Override
  public List<Person> getAll() {
    LOG.info("Getting all persons.");
    List<Person> personList = new ArrayList<>();
    personRepository.findAll().forEach(personList::add);
    return personList;
  }

  public Person save(Person p) {
    LOG.info("Saving new person.");
    if (isUserExist(p.getEmail()))
      throw new ApiRequestException("User already exist.");
    return personRepository.save(p);
  }

  @Override
  public Person findById(Long personId) {
    LOG.info("Find person by id.");
    Optional<Person> dbPerson = personRepository.findById(personId);
    if(!dbPerson.isPresent())
      throw new NotFoundException("User not found.");
    return dbPerson.get();
  }

  @Override
  public Boolean isUserExist(String email) {
    return personRepository.findByEmail(email).isPresent()?true:false;
  }
}
