package com.group3.lovelacehotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRegistrationDto {
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String password;

}
