package com.dima.spring.entity;

import com.dima.spring.validation.DateCheck;
import org.springframework.validation.annotation.Validated;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="user")
@Validated
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Long Id;

    @Column(name="First_Name")
    @NotBlank
    private String FirstName;

    @Column(name="Last_Name")
    @NotBlank
    private String LastName;

    @Column(name="Second_Name")
    @NotBlank
    private String SecondName;

    @Column(name="Date_Of_Birth")
    @NotNull
    @DateCheck(message = "The birth date must be greater or equal than 18")
    private LocalDate DateOfBirth;


    public User(){}
//
    public User(String first_name, String last_name, String second_name,LocalDate date_of_birth) {
        FirstName = first_name;
        LastName = last_name;
        SecondName = second_name;
        DateOfBirth = date_of_birth;
    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getSecondName() {
        return SecondName;
    }

    public void setSecondName(String secondName) {
        SecondName = secondName;
    }

    public LocalDate getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }
}

