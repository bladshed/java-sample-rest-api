package com.bayzdelivery.repositories;

import com.bayzdelivery.BayzDeliveryApplication;
import com.bayzdelivery.model.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.StreamSupport;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BayzDeliveryApplication.class)
@ActiveProfiles(value = "test")
@Sql({"/schema.sql", "/data.sql"})
@Transactional
public class PersonRepositoryTest {

    @Autowired
    PersonRepository personRepository;

    @Test
    public void testFindByUsername(){
        Optional<Person> person = personRepository.findByUsername("test");

        Assert.assertEquals("test", person.get().getUsername());
    }

    @Test
    public void testGetPersonByEmail(){
        Optional<Person> person = personRepository.findByEmail("francis@yahoo.com");

        Assert.assertEquals("francis@yahoo.com", person.get().getEmail());
    }

    @Test
    public void testPersonSize(){
        Iterable<Person> persons = personRepository.findAll();

        Assert.assertEquals(11, StreamSupport.stream(persons.spliterator(), false).count());
    }
}
