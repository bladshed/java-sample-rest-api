package com.bayzdelivery.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * Created By Mary Ellen Bowman
 */
public class LoginDto {
    @NotNull
    private String username;

    @NotNull
    private String password;

    String name;

    String email;

    Character type;

    String registrationNumber;

    /**
     * Default constructor
     */
    protected LoginDto() {
    }

    /**
     * Partial constructor
     * @param username
     * @param password
     */
    public LoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Full constructor
     * @param username
     * @param password
     */
    public LoginDto(String username, String password, String name, String email, Character type, String registrationNumber) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.type = type;
        this.registrationNumber = registrationNumber;
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
}
