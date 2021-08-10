package com.group3.lovelacehotel.search;

import com.group3.lovelacehotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface SearchRepository extends JpaRepository<Room, Long>, JpaSpecificationExecutor<Room> {


    @Query(value = "SELECT rooms.id * FROM rooms " +
            "LEFT JOIN reservations " +
            "ON ( reservations.check_in_date <= expected_check_in_date " +
            "AND reservations.check_out_date >= expected_check_out_date)" +
            "WHERE reservations.id IS NULL AND rooms.number_of_adults >= planned_number_of_adults " +
            "AND rooms.number_of_children >= planned_number_of_children", nativeQuery = true)


    Optional<Room> searchAvailableRoom(@Param("expected_check_in_date") LocalDateTime expectedCheckInDate,
                                       @Param("expected_check_out_date") LocalDateTime expectedCheckOutDate,
                                       @Param("planned_number_of_adults") Long plannedNumberOfAdults,
                                       @Param("planned_number_of_children") Long plannedNumberOfChildren);

}
