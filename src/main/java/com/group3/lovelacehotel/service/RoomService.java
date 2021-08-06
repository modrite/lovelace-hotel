package com.group3.lovelacehotel.service;

import com.group3.lovelacehotel.model.Room;

import java.util.Collection;

public interface RoomService {

    Collection<Room> getAll();

    Room getById(Long id);

    Room updateRoom(Long id, Room updatedRoom);

    Room register(Room room);

    void delete(Long id);
}
