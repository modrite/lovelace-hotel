package com.group3.lovelacehotel.service;

import com.group3.lovelacehotel.exception.BadRequestException;
import com.group3.lovelacehotel.model.Room;
import com.group3.lovelacehotel.model.RoomSearch;
import com.group3.lovelacehotel.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.matchingAll;

@Transactional
@RequiredArgsConstructor
@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;


    public List<Room> search(RoomSearch roomSearch) {
        Room room = new Room();
        room.setRoomSize(roomSearch.getRoomSize());
        room.setType(roomSearch.getType());

        Example<Room> roomExample = Example.of(room, matchingAll().withIgnoreNullValues());

        return roomRepository.findAll(roomExample);

    }

    @Override
    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room getById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));
    }

    @Override
    public Room updateRoom(Long id, Room updatedRoom) {
        Room existingRoom = roomRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Invalid room id " + id));

        existingRoom.setRoomSize(updatedRoom.getRoomSize());
        existingRoom.setType(updatedRoom.getType());
        existingRoom.setIfBooked(updatedRoom.getIfBooked());

        return roomRepository.save(existingRoom);
    }

    @Override
    public void delete(Long id) {
        roomRepository.deleteById(id);
    }

    public boolean doesRoomIdExist(Long id) {
        return roomRepository.existsById(id);
    }

    public Optional<Room> findByRoomSize(String roomSize) {
        return roomRepository.findByRoomSize(roomSize);
    }

    public Optional<Room> findByTypeAndRoomSize(String type, String roomSize) {
        return roomRepository.findByTypeAndRoomSize(type, roomSize);
    }

}
