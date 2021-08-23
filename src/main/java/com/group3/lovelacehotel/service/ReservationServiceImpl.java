package com.group3.lovelacehotel.service;

import com.group3.lovelacehotel.exception.BadRequestException;
import com.group3.lovelacehotel.model.Reservation;
import com.group3.lovelacehotel.model.ReservationSearch;
import com.group3.lovelacehotel.model.Room;
import com.group3.lovelacehotel.repository.ReservationRepository;
import com.group3.lovelacehotel.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.matchingAll;

@Transactional
@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;


    public List<Reservation> search(ReservationSearch reservationSearch) {
        Reservation reservation = new Reservation();
        reservation.setType(reservationSearch.getType());
        reservation.setCheckInDate(reservationSearch.getCheckInDate().toLocalDate());
        reservation.setCheckOutDate(reservationSearch.getExpectedCheckOutDate().toLocalDate());
        reservation.setStayNights(reservationSearch.getStayNights());

        Example<Reservation> reservationExample = Example.of(reservation, matchingAll().withIgnoreNullValues());

        return reservationRepository.findAll(reservationExample);
    }


    @Override
    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation confirm(Reservation reservation) {
        Room room = roomRepository.getById(reservation.getRoomId());
        Long numberOfNights = reservation.getCheckInDate().until(reservation.getCheckOutDate(), ChronoUnit.DAYS);

        Double totalPrice = numberOfNights * room.getPrice();

        reservation.setRoom(room);
        reservation.setType(room.getType());
        reservation.setStayNights(numberOfNights);
        reservation.setPrice(totalPrice);


        var savedReservation = reservationRepository.save(reservation);
        room.setIfBooked(true);

        roomRepository.save(room);

        return savedReservation;
    }

    @Override
    public List<Reservation> getAll() {
        return reservationRepository.findAllByOrderByIdDesc();
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

        Room room = roomRepository.getById(updatedReservation.getRoomId());
        Long numberOfNights = updatedReservation.getCheckInDate().until(updatedReservation.getCheckOutDate(), ChronoUnit.DAYS);

        existingReservation.setType(updatedReservation.getType());
        existingReservation.setCheckInDate(updatedReservation.getCheckInDate());
        existingReservation.setCheckOutDate(updatedReservation.getCheckOutDate());
        existingReservation.setStayNights(numberOfNights);
        existingReservation.setNumberOfAdults(updatedReservation.getNumberOfAdults());
        existingReservation.setNumberOfChildren(updatedReservation.getNumberOfChildren());

        existingReservation.setCustomerEmail(updatedReservation.getCustomerEmail());
        existingReservation.setCustomerName(updatedReservation.getCustomerName());
        existingReservation.setCustomerSurname(updatedReservation.getCustomerSurname());
        existingReservation.setCustomerPhoneNumber(updatedReservation.getCustomerPhoneNumber());

        existingReservation.setPrice(updatedReservation.getPrice());
        existingReservation.setRoom(room);


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
