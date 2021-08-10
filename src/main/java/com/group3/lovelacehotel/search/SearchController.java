package com.group3.lovelacehotel.search;

import com.group3.lovelacehotel.model.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/roomsHotel")
public class SearchController {

    private final SearchRepository searchRepository;


    @GetMapping("/roomsHotel")
    public String customerSearchRoom(@RequestParam LocalDateTime expectedCheckInDate,
                                     @RequestParam LocalDateTime expectedCheckOutDate,
                                     @RequestParam Long numberOfAdults,
                                     @RequestParam Long numberOfChildren,
                                     Model model) {
        Optional<Room> foundRooms = searchRepository.searchAvailableRoom(expectedCheckInDate,
                expectedCheckOutDate, numberOfAdults, numberOfChildren);
        model.addAttribute("rooms", foundRooms);
        return "/roomsHotel";

    }



}
