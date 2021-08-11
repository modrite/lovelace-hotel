package com.group3.lovelacehotel.repository;

import com.group3.lovelacehotel.model.Reservation;
import com.group3.lovelacehotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findById(Long id);

    @Query("Select r.room from Reservation  r where r.checkOutDate <:date")
    Set<Room> availableRooms(@Param("date") LocalDateTime date);

    @Query("Select r.room from Reservation  r where " +
            "r.checkOutDate <=:date " +
            "and r.room.numberOfAdults=:numberOfAdults " +
            "and r.room.numberOfChildren=:numberOfChildren")
    Set<Room> availableRoomsByParam(@Param("date") LocalDateTime date,
                             @Param("numberOfAdults") Long numberOfAdults,
                             @Param("numberOfChildren") Long numberOfChildren);
}
