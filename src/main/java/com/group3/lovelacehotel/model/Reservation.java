package com.group3.lovelacehotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "room_id")
    Room room;

    @NotBlank
    private Long stayNights;

    @NotBlank
    private String type;

    private Double price;

    private Double totalPrice;

    @NotBlank
    private LocalDateTime checkInDate;

    @NotBlank
    private LocalDateTime expectedCheckOutDate;

    //    @NotBlank
//    @Column(name = "number_of_rooms")
//    private Long numberOfRooms;
//
//    @NotBlank
//    @Column(name = "number_of_adults")
//    @Min(value = 1)
//    private Long numberOfAdults;
//
//    @NotBlank
//    @Column(name = "number_of_children")
//    @Min(value = 0)
//    private Long numberOfChildren;


}
