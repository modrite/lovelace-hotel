package com.group3.lovelacehotel.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "occupied")
    private Boolean ifBooked;

    private String roomSize;

    private Double price;

    private Long numberOfAdults;

    private Long numberOfChildren;

}
