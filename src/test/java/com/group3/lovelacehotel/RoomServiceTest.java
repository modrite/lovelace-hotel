package com.group3.lovelacehotel;

import com.group3.lovelacehotel.model.Room;
import com.group3.lovelacehotel.repository.RoomRepository;
import com.group3.lovelacehotel.service.RoomServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class RoomServiceTest {

    @InjectMocks
    RoomServiceImpl roomServiceImpl;
    @Mock
    RoomRepository roomRepository;


    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerRoom() {

        Room singleRoom = new Room(1L, false, "Single room", 90.00, 1L, 0L);
        Room familyRoom = new Room(2L, false, "Family room", 120.00, 2L, 3L);

        when(roomRepository.save(singleRoom)).thenReturn(familyRoom);
        assertEquals(familyRoom, roomServiceImpl.register(singleRoom));
        verify(roomRepository).save(singleRoom);
    }


    @Test
    void getRoomById() {

        Room familyRoom = new Room(3L, false, "Family room", 120.00, 2L, 3L);
        Optional<Room> room = Optional.ofNullable(familyRoom);
        when(roomRepository.findById(anyLong())).thenReturn(room);
        assertEquals(familyRoom, roomServiceImpl.getById(anyLong()));
    }

    @Test
    void testGetRoomByIdForNull() {
        when(roomRepository.findById(anyLong())).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, () -> {
            roomServiceImpl.getById(anyLong());
        });

    }

    @Test
    void deleteRoomById() {
        Room familyRoom = new Room(2L, false, "Family room", 120.00, 2L, 3L);
        doNothing().when(roomRepository).deleteById(2L);
        roomServiceImpl.delete(2L);

        verify(roomRepository).deleteById(2L);
    }

    @Test
    void getAllRooms() {
        when(roomRepository.findAll()).thenReturn(
                Arrays.asList(new Room(2L, false, "Family room", 120.00, 2L, 3L),
                        new Room(1L, false, "Single room", 90.00, 1L, 0L)));


        List<Room> rooms = roomServiceImpl.getAll();
        assertEquals(2, rooms.size());
        assertEquals(false, rooms.get(0).getIfBooked());
        assertEquals("Family room", rooms.get(0).getType());
        assertEquals(2L, rooms.get(0).getNumberOfAdults());
        assertEquals(3L, rooms.get(0).getNumberOfChildren());
        assertEquals(120.00, rooms.get(0).getPrice());
        assertTrue(rooms.contains(new Room(1L, false, "Single room", 90.00, 1L, 0L)));

        verify(roomRepository).findAll();
    }

    @Test
    void updateRoomTest() {
        Long id = 1L;
        Room oldRoom = new Room(1L, false, "Single room", 90.00, 1L, 0L);
        Room update = new Room(2L, false, "Family room", 120.00, 2L, 3L);

        when(roomRepository.findById(id)).thenReturn(Optional.of(oldRoom));
        oldRoom.setIfBooked(update.getIfBooked());
        oldRoom.setType(update.getType());
        oldRoom.setPrice(update.getPrice());
        oldRoom.setNumberOfAdults(update.getNumberOfAdults());
        oldRoom.setNumberOfChildren(update.getNumberOfChildren());

        when(roomRepository.save(oldRoom)).thenReturn(oldRoom);

        Room updatedRoom = roomServiceImpl.updateRoom(id, update);

        assertEquals(updatedRoom, oldRoom);
        assertNotNull(updatedRoom);

        verify(roomRepository).save(oldRoom);
        verify(roomRepository).findById(id);

    }

    @Test
    void updateRoomWhenNoRoomFound() {
        Long id = 2L;
        Room update = new Room(id, false, "Single room", 90.00, 1L, 0L);

        when(roomRepository.findById(2L))
                .thenReturn(Optional.empty());

        try {
            roomServiceImpl.updateRoom(2L, update);
            fail();
        } catch (Exception e) {
            assertEquals("Invalid room id " + id, e.getMessage());
        }

        verify(roomRepository).findById(2L);
    }


}