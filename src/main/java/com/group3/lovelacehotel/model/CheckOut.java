package com.group3.lovelacehotel.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="checkOut")

public class CheckOut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "reservation_id")
    Reservation reservation;

    private LocalDateTime checkOutDate;

//    @Column (name = "fine_amount")
//    private Double fineAmount;

}
