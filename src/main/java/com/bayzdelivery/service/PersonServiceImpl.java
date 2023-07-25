package com.bayzdelivery.service;

import com.bayzdelivery.exceptions.ApiRequestException;
import com.bayzdelivery.exceptions.NotFoundException;
import com.bayzdelivery.model.LoginDto;
import com.bayzdelivery.model.Person;
import com.bayzdelivery.model.Role;
import com.bayzdelivery.repositories.JwtProvider;
import com.bayzdelivery.repositories.PersonRepository;
import com.bayzdelivery.repositories.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService{

  private static final Logger LOG = LoggerFactory.getLogger(PersonServiceImpl.class);

  private PersonRepository personRepository;
  private AuthenticationManager authenticationManager;
  private RoleRepository roleRepository;

  private PasswordEncoder passwordEncoder;

  private JwtProvider jwtProvider;

  @Autowired
  public PersonServiceImpl(PersonRepository personRepository, AuthenticationManager authenticationManager,
                           RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
    this.personRepository = personRepository;
    this.authenticationManager = authenticationManager;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtProvider = jwtProvider;
  }

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

  /**
   * Sign in a user into the application, with JWT-enabled authentication
   *
   * @param username  username
   * @param password  password
   * @return Optional of the Java Web Token, empty otherwise
   */
  @Override
  public String signin(String username, String password) {
    LOG.info("New user attempting to sign in");
    Optional<String> token = Optional.empty();
    Optional<Person> person = personRepository.findByUsername(username);
    if (person.isPresent()) {
      try {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        token = Optional.of(jwtProvider.createToken(username, person.get().getRoles()));
      } catch (AuthenticationException e){
        LOG.info("Log in failed for user {}", username);
      }
    }

    if(!token.isPresent())
      throw new HttpServerErrorException(HttpStatus.FORBIDDEN, "Login Failed");

    return token.get();
  }

  /**
   * Create a new user in the database.
   *
   * @param loginDto login object
   * @return Optional of user, empty if the user already exists.
   */
  @Override
  public Person signup(LoginDto loginDto) {
    LOG.info("New user attempting to sign in");
    if (isUserExist(loginDto.getEmail()))
      throw new ApiRequestException("User already exist.");

    Optional<Person> user = Optional.empty();
    if (!personRepository.findByUsername(loginDto.getUsername()).isPresent()) {
      Optional<Role> role = roleRepository.findByRoleName("ROLE_USER");
      //String username, String password, String name, String email,
      //                Character type, String registrationNumber, List<Role> roles
      user = Optional.of(
        personRepository.save(
          new Person(
                  loginDto.getUsername(),
                  passwordEncoder.encode(loginDto.getPassword()),
                  loginDto.getName(),
                  loginDto.getEmail(),
                  loginDto.getType(),
                  loginDto.getRegistrationNumber(),
                  role.get()
          )
        )
      );
    }
    return user.get();
  }
}
