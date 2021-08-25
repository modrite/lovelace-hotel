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
            "and r.numberOfAdults >=:numberOfAdults " +
            "and r.numberOfChildren >=:numberOfChildren ")
    Set<Room> getAvailableRoomsByParams(@Param("numberOfAdults") Long numberOfAdults,
                                        @Param("numberOfChildren") Long numberOfChildren);


}
