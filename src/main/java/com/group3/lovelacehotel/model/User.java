package com.group3.lovelacehotel.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private Boolean active;
    private String roles;

}
