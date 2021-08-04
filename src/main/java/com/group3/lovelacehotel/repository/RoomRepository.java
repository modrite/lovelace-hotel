package com.group3.lovelacehotel.repository;

import com.group3.lovelacehotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>, JpaSpecificationExecutor<Room> {

    Optional<Room> findByRoomSize(String roomSize);

    Optional<Room> findByTypeAndRoomSize(String type, String roomSize);

    List<Room> findAllByRoomSize(String roomSize);


}
