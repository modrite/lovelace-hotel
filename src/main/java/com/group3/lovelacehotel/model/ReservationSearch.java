package com.group3.lovelacehotel.model;

import lombok.Data;

import java.util.Date;

@Data
public class ReservationSearch {

    private Long id;

    private Long stayNights;

    private String roomSize;

    private Long price;

    private Long numberOfRooms;

    private Long persons;

    private Long numberOfChildren;

    private Date arrivalDate;

}
