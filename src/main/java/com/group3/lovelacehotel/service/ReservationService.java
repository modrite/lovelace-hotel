package com.group3.lovelacehotel.service;

import com.group3.lovelacehotel.model.Reservation;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface ReservationService {

    Collection<Reservation> getAll();

    Reservation getById(Long id);

    Reservation updateReservation(Long id, Reservation updatedReservation);

    Reservation saveReservation(Reservation reservation);

    Reservation confirm(Reservation reservation);




    void delete(Long id);

}
