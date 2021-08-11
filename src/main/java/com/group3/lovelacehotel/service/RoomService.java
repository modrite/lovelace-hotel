package com.group3.lovelacehotel.service;

import com.group3.lovelacehotel.model.Room;
import com.group3.lovelacehotel.model.RoomSearch;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;


public interface RoomService {

    Collection<Room> getAll();

    Room getById(Long id);

    Room updateRoom(Long id, Room updatedRoom);

    Room register(Room room);

    void delete(Long id);

    Set<Room> availableRooms(RoomSearch roomSearch);

}
