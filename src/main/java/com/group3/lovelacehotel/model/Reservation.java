package com.group3.lovelacehotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @NotNull
    private Long stayNights;

    @NotBlank
    private String type;

    private Double price;

    private Double totalPrice;

    @NotNull
    private LocalDateTime checkInDate;

    @NotNull
    private LocalDateTime checkOutDate;

    @Transient
    private Long roomId;

    @Transient
    private Long userId;

    //    @NotBlank
//    @Column(name = "number_of_rooms")
//    private Long numberOfRooms;
//
    @NotNull
    private Long numberOfAdults;
    @NotNull
    private Long numberOfChildren;


}
