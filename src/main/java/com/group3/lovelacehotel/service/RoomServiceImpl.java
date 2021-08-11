package com.group3.lovelacehotel.service;

import com.group3.lovelacehotel.exception.BadRequestException;
import com.group3.lovelacehotel.model.Room;
import com.group3.lovelacehotel.model.RoomSearch;
import com.group3.lovelacehotel.repository.ReservationRepository;
import com.group3.lovelacehotel.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.springframework.data.domain.ExampleMatcher.matchingAll;

@Transactional
@RequiredArgsConstructor
@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public Set<Room> availableRooms(RoomSearch roomSearch) {
        return availableRooms(
                roomSearch.getCheckInDate(),
                roomSearch.getNumberOfAdults(),
                roomSearch.getNumberOfChildren());
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

        existingRoom.setType(updatedRoom.getType());
        existingRoom.setPrice(updatedRoom.getPrice());
        existingRoom.setIfBooked(updatedRoom.getIfBooked());
        existingRoom.setNumberOfAdults(updatedRoom.getNumberOfAdults());
        existingRoom.setNumberOfChildren(updatedRoom.getNumberOfChildren());

        return roomRepository.save(existingRoom);
    }

    @Override
    public Room register(Room room) {
        room.setIfBooked(false);
        return roomRepository.save(room);
    }

    @Override
    public void delete(Long id) {
        roomRepository.deleteById(id);
    }

    public boolean doesRoomIdExist(Long id) {
        return roomRepository.existsById(id);
    }

    public Optional<Room> findById(Long id) {
        return roomRepository.findById(id);
    }

    private Set<Room> availableRooms(LocalDateTime date) {
        var rooms = roomRepository.findByIfBookedFalse();
        var checkedOutRooms = reservationRepository.availableRooms(date);
        rooms.addAll(checkedOutRooms);

        return rooms;
    }

    private Set<Room> availableRooms(LocalDate date,
                                     Long numberOfAdults,
                                     Long numberOfChildren) {
        var dateTime = date.atStartOfDay();
        var rooms = roomRepository.getAvailableRoomsByParams(numberOfAdults, numberOfChildren);
        var checkedOutRooms = reservationRepository.availableRoomsByParam(dateTime, numberOfAdults, numberOfChildren);
        rooms.addAll(checkedOutRooms);

        return rooms;
    }

}
