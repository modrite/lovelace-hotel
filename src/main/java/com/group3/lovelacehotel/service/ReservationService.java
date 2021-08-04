package com.group3.lovelacehotel.service;

import com.group3.lovelacehotel.model.Reservation;

import java.util.Collection;

public interface ReservationService {

    Collection<Reservation> getAll();

    Reservation getById(Long id);

    Reservation updateReservation(Long id, Reservation updatedReservation);

    void delete(Long id);
}
