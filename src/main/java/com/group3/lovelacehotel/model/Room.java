package com.group3.lovelacehotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "occupied")
    private Boolean ifBooked;

//    @OneToOne
//    @JoinColumn(name = "customer_id")
//    private User user;

    private String type;

    private Double price;

    private Long numberOfAdults;

    private Long numberOfChildren;

}
