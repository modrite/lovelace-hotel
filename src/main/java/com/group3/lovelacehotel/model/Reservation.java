package com.group3.lovelacehotel.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Entity
@Table(name="reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private Long stayNights;

    @NotBlank
    private String roomSize;

    private Long price;

    @NotBlank
    @Column(name = "number_of_rooms")
    private Long numberOfRooms;

    @NotBlank
    @Column(name = "number_of_persons")
    private Long persons;

    @NotBlank
    @Column(name = "number_of_children")
    private Long numberOfChildren;

    @NotBlank
    @Column(name = "reservation_from_date")
    private Date arrivalDate;


}
