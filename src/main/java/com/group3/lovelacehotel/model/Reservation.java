package com.group3.lovelacehotel.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
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
    @Column(name = "number_of_adults")
    @Min(value = 1)
    private Long numberOfAdults;

    @NotBlank
    @Column(name = "number_of_children")
    @Min(value = 0)
    private Long numberOfChildren;

    @NotBlank
    @Column(name = "reservation_from_date")
    private Date checkInDate;

    @NotBlank
    @Column(name = "reservation_to_date")
    private Date checkOutDate;


}
