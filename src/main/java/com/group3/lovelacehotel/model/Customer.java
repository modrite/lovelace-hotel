package com.group3.lovelacehotel.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name="customers")
public class Customer {

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please provide your name")
    private String name;

    @NotBlank(message = "Please provide your surname")
    private String surname;

    @Column(name = "phone_number")
    @NotBlank(message = "Please provide your phone number")
    private String phoneNumber;

    @Email(message="please provide a valid email address")
    @NotBlank(message = "Please provide your email address")
    private String email;

    @Size(min = 8, message = "Password length should be at least 8 digits long")
    private String password;

}
