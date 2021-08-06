package com.group3.lovelacehotel.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "customers", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please provide your name")
    private String name;

    @NotBlank(message = "Please provide your surname")
    private String surname;

    @NotBlank(message = "Please provide your phone number")
    private String phoneNumber;

    @Email(message = "please provide a valid email address")
    @NotBlank(message = "Please provide your email address")
    private String email;

    @Size(min = 8, message = "Password length should be at least 8 digits long")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "customers_roles",
            joinColumns = @JoinColumn(
                    name = "customer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))

    private Collection<Role> roles;


    public Customer(String name, String surname, String phoneNumber, String email, String password, Collection<Role> roles) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}

