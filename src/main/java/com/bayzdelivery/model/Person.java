package com.bayzdelivery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "person")
public class Person implements Serializable{

  private static final long serialVersionUID = 432154291451321L;

  public Person() {}

  public Person(String username, String password, String name, String email,
                Character type, String registrationNumber, Role role) {
    this.username = username;
    this.password = password;
    this.name = name;
    this.email = email;
    this.type = type;
    this.registrationNumber = registrationNumber;
    this.roles = Arrays.asList(role);
  }

  public Person(Long id, String username, String password, String name, String email, Character type, String registrationNumber, Role role) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.name = name;
    this.email = email;
    this.type = type;
    this.registrationNumber = registrationNumber;
    this.roles = Arrays.asList(role);
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  @JsonIgnore
  private String password;

  @NotNull
  @Column(name = "name")
  String name;

  @NotNull
  @Email
  @Column(name = "email")
  String email;

  @NotNull
  @Column(name = "type")
  Character type;

  @NotNull
  @Column(name = "registration_number")
  String registrationNumber;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "person_role",
          joinColumns = @JoinColumn(name = "person_id",
                  referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "role_id",
                  referencedColumnName = "id")
  )
  private List<Role> roles;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Character getType() {
    return type;
  }

  public void setType(Character type) {
    this.type = type;
  }

  public String getRegistrationNumber() {
    return registrationNumber;
  }

  public void setRegistrationNumber(String registrationNumber) {
    this.registrationNumber = registrationNumber;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  @Override
  public String toString() {
    return "Person{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", type=" + type +
            ", registrationNumber='" + registrationNumber + '\'' +
            ", roles=" + roles +
            '}';
  }
}
