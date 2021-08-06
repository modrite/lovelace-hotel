package com.group3.lovelacehotel.service;

import com.group3.lovelacehotel.exception.BadRequestException;
import com.group3.lovelacehotel.model.Reservation;
import com.group3.lovelacehotel.model.ReservationSearch;
import com.group3.lovelacehotel.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.matchingAll;

@Transactional
@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;


    public List<Reservation> search(ReservationSearch reservationSearch) {
        Reservation reservation = new Reservation();
        reservation.setRoomSize(reservationSearch.getRoomSize());
        reservation.setCheckInDate(reservationSearch.getCheckInDate());
        reservation.setExpectedCheckOutDate(reservationSearch.getExpectedCheckOutDate());
        reservation.setStayNights(reservationSearch.getStayNights());

        Example<Reservation> reservationExample = Example.of(reservation, matchingAll().withIgnoreNullValues());

        return reservationRepository.findAll(reservationExample);

    }

    @Override
    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
    }

    @Override
    public Reservation updateReservation(Long id, Reservation updatedReservation) {
        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Invalid reservation id " + id));

        existingReservation.setRoomSize(updatedReservation.getRoomSize());
        existingReservation.setCheckInDate(updatedReservation.getCheckInDate());
        existingReservation.setExpectedCheckOutDate(updatedReservation.getExpectedCheckOutDate());
        existingReservation.setStayNights(updatedReservation.getStayNights());
        existingReservation.setTotalPrice(updatedReservation.getTotalPrice());
        existingReservation.setPrice(updatedReservation.getPrice());
        existingReservation.setCustomer(updatedReservation.getCustomer());
        existingReservation.setRoom(updatedReservation.getRoom());


        return reservationRepository.save(existingReservation);
    }

    @Override
    public void delete(Long id) {
        reservationRepository.deleteById(id);
    }

    public boolean doesRoomIdExist(Long id) {
        return reservationRepository.existsById(id);
    }

    public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
    }


}
