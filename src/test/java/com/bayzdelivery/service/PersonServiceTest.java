package com.bayzdelivery.service;

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
import java.util.stream.StreamSupport;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value = "test")
@Sql({"/schema.sql", "/data.sql"})
@Transactional
public class PersonServiceTest {

    @Autowired
    PersonService personService;

    @Test
    public void testFindById(){
        Person person = personService.findById(11L);

        Assert.assertEquals("Test", person.getName());
    }

    @Test
    public void testIfUserExist(){
        Boolean isUserExist = personService.isUserExist("test@yahoo.com");

        Assert.assertEquals(true, isUserExist);
    }

    @Test
    public void testPersonSize(){
        Iterable<Person> persons = personService.getAll();

        Assert.assertEquals(11, StreamSupport.stream(persons.spliterator(), false).count());
    }
}
