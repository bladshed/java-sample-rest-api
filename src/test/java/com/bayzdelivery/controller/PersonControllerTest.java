package com.bayzdelivery.controller;

import com.bayzdelivery.model.Person;
import com.bayzdelivery.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  private PersonService personService;

  @Test
  public void testPersonShouldBeRegistered() throws Exception {

    Person person = new Person();
    person.setId(9L);
    person.setName("Bitoy");
    person.setEmail("bitoy@yahoo.com");
    person.setType('D');
    person.setRegistrationNumber("510btyum");

    when(personService.save(new Person()))
            .thenReturn(person);

    RequestBuilder request = MockMvcRequestBuilders
            .post("/person")
            .content("{\"name\":\"Bitoy\",\"email\":\"bitoy@yahoo.com\",\"type\":\"D\",\"registrationNumber\":\"510btyum\"}")
            .contentType(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(request)
            .andExpect(status().isOk())
            .andReturn();
  }

  @Test
  public void getPersonById() throws Exception {
    Person person = new Person();
    person.setId(1L);
    person.setName("Kenzo");
    person.setEmail("kenzo@yahoo.com");
    person.setType('C');
    person.setRegistrationNumber("777regnum");

    when(personService.findById(1L))
            .thenReturn(person);

    RequestBuilder request = MockMvcRequestBuilders
            .get("/person/1")
            .accept(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(content().json("{\"id\":1,\"name\":\"Kenzo\",\"email\":\"kenzo@yahoo.com\",\"type\":\"C\",\"registrationNumber\":\"777regnum\"}"))
            .andReturn();
  }

}
