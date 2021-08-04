package com.group3.lovelacehotel.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RoomSearch {

    private Long id;

    private String roomSize;

    private Double price;

    private Long numberOfAdults;

    private Long numberOfChildren;

    private LocalDateTime checkInDate;

    private LocalDateTime checkOutDate;

    private Boolean isNotOccupied;
}
