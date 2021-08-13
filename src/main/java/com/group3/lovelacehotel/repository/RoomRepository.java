package com.group3.lovelacehotel.repository;

import com.group3.lovelacehotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>, JpaSpecificationExecutor<Room> {

    Optional<Room> findByType(String type);


    List<Room> findAllByType(String type);

    Set<Room> findByIfBookedFalse();

    @Query("select r from Room r where " +
            "r.ifBooked=0 " +
            "and r.numberOfAdults <=numberOfAdults " +
            "and r.numberOfChildren<=numberOfChildren ")
    Set<Room> getAvailableRoomsByParams(@Param("numberOfAdults") Long numberOfAdults,
                                        @Param("numberOfChildren") Long numberOfChildren);

//    @Query(value = "SELECT * FROM rooms as r" +
//            "LEFT JOIN reservations as reserv" +
//            "ON reserv.room_id = r.id " +
//            "WHERE( (r.occupied = false) OR " +
//            " ( reserv.checkOutDate >= :expectedCheckInDate)" +
//            " AND" +
//            " r.number_of_adults >= :planned_number_of_adults" +
//            " AND r.number_of_children >= :planned_number_of_children)", nativeQuery = true)
//    Set<Room> searchAvailableRoom(@Param("expected_check_in_date") LocalDateTime expectedCheckInDate,
//                                  @Param("planned_number_of_adults") Long plannedNumberOfAdults,
//                                  @Param("planned_number_of_children") Long plannedNumberOfChildren);
}
