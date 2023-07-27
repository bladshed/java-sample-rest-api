package com.bayzdelivery.controller;

import com.bayzdelivery.BayzDeliveryApplication;
import com.bayzdelivery.model.LoginDto;
import com.bayzdelivery.model.Person;
import com.bayzdelivery.model.Role;
import com.bayzdelivery.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@RunWith(SpringRunner.class)
@SpringBootTest(
  classes = {
    BayzDeliveryApplication.class,
    PersonController.class
  },
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles(value = "test")
public class PersonControllerTest {

  private LoginDto signupDto = new LoginDto(
          "test", "password", "Test",
          "test@yahoo.com", 'C', "123TST");

  private Person person = new Person(signupDto.getUsername(), signupDto.getPassword(), signupDto.getName(),
          signupDto.getEmail(), signupDto.getType(), signupDto.getRegistrationNumber(), new Role());

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private JwtRequestHelper jwtRequestHelper;

  @MockBean
  private PersonService personService;

  @Test
  public void signin() {
    restTemplate.postForEntity("/person/signin", new LoginDto("admin", "myPass"), Void.class);
    verify(this.personService).signin("admin","myPass");
  }

  @Test
  public void allowSignupForRoleAdmin(){
    when(personService.signup(signupDto)).thenReturn(person);

    ResponseEntity<Person> response = restTemplate.exchange("/person/signup",
            POST,
            new HttpEntity(signupDto,jwtRequestHelper.withRole("ROLE_ADMIN")),
            Person.class);

    assertThat(response.getStatusCode().value(), is(200));
  }

  @Test
  public void forbiddenSignupForRoleUser(){
    when(personService.signup(signupDto)).thenReturn(person);

    ResponseEntity<Person> response = restTemplate.exchange("/person/signup",
            POST,
            new HttpEntity(signupDto,jwtRequestHelper.withRole("ROLE_USER")),
            Person.class);

    assertThat(response.getStatusCode().value(), is(403));
  }

  @Test
  public void testGetPersonById(){
    person.setId(1L);
    when(personService.findById(1L)).thenReturn(person);

    ResponseEntity<Person> response = restTemplate.exchange("/person/1",
            GET,
            new HttpEntity(null,jwtRequestHelper.withRole("ROLE_ADMIN")),
            Person.class);

    assertThat(response.getStatusCode().value(), is(200));
    assertThat(response.getBody().getId(), is(person.getId()));
    assertThat(response.getBody().getUsername(), is(person.getUsername()));
    assertThat(response.getBody().getName(), is(person.getName()));
  }

}
