package com.group3.lovelacehotel.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationSearch {

    private Long id;

    private Long stayNights;

    private String type;

    private Double price;

    private Long numberOfRooms;

    private Long numberOfAdults;

    private Long numberOfChildren;

    private LocalDateTime checkInDate;

    private LocalDateTime expectedCheckOutDate;

}
