package com.bayzdelivery.controller;

import com.bayzdelivery.model.Person;
import com.bayzdelivery.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Person service", description = "Person APIs with description tag annotation")
@RestController
@RequestMapping("/person")
public class PersonController {

  @Autowired
  PersonService personService;

  @GetMapping
  @Operation(summary = "Get all Person / User")
  @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK") })
  public ResponseEntity<List<Person>> getAllPersons() {
    return ResponseEntity.ok(personService.getAll());
  }

  @GetMapping(path = "/{person-id}")
  @Operation(summary = "Register new Person / User by id")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "OK"),
          @ApiResponse(responseCode = "404", description = "User not found",
                  content = @Content(examples = {
                          @ExampleObject(name = "getPersonById",
                          summary = "User not found",
                          description = "User not found",
                          value = "[{\n" +
                                  "  \"status\": 404,\n" +
                                  "  \"error\": \"NOT_FOUND\",\n" +
                                  "  \"message\": \"User not found.\"\n" +
                                  "}]")
                  })
          )
  })
  public ResponseEntity<Person> getPersonById(@PathVariable(name="person-id", required=true)Long personId) {
    return ResponseEntity.ok(personService.findById(personId));
  }

  @PostMapping
  @Operation(summary = "Register new Person / User")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "OK"),
          @ApiResponse(responseCode = "400", description = "User already exist",
                  content = @Content(examples = {
                          @ExampleObject(name = "register",
                                  summary = "User already exist",
                                  description = "User already exist",
                                  value = "[{\n" +
                                          "  \"status\": 400,\n" +
                                          "  \"error\": \"BAD_REQUEST\",\n" +
                                          "  \"message\": \"User already exist.\"\n" +
                                          "}]")
                  })
          )
  })
  public ResponseEntity<Person> register(@RequestBody Person p) {
    p.setType(Character.toUpperCase(p.getType()));
    return ResponseEntity.ok(personService.save(p));
  }

}
