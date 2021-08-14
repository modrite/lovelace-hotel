package com.group3.lovelacehotel.controller;


import com.group3.lovelacehotel.model.Reservation;
import com.group3.lovelacehotel.model.Room;
import com.group3.lovelacehotel.model.RoomSearch;
import com.group3.lovelacehotel.model.User;
import com.group3.lovelacehotel.repository.UserRepository;
import com.group3.lovelacehotel.service.ReservationService;
import com.group3.lovelacehotel.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;
    private final UserRepository userRepository;
    private final ReservationService reservationService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping
    public String index(Model model) {
        model.addAttribute("rooms", roomService.getAll());
        return "rooms";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/add-room")
    public String addRoom(Model map, Room room, @AuthenticationPrincipal UserDetails userDetails) {

        map.addAttribute("pageName", "Add new room");

        return "add-room";

    }

    @GetMapping("/edit/{id}")
    public String editRoom(@PathVariable("id") Long id, Model model) {
        model.addAttribute("pageName", "Edit room");

        Room room = roomService.getById(id);
        model.addAttribute("room", room);
        return "edit-room";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") Long id, Model model) {

        roomService.delete(id);

        return index(model);
    }

    @PostMapping
    public String register(@Valid Room room, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-room";
        }

        roomService.register(room);

        return index(model);
    }

    @PostMapping("/update/{id}")
    public String updateRoom(@PathVariable("id") Long id, @Valid Room room, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit-room";
        }

        roomService.updateRoom(id, room);

        return index(model);
    }

    //search room section
    @GetMapping("/reservation")
    public String searchRoom(Model model, RoomSearch roomSearch) {
        model.addAttribute("pageName", "Search room");
        model.addAttribute("roomSearch", roomSearch);
        /** /rooms/search - GET **/
        return "reservation";
    }

    @PostMapping("/reservation")
    public String searchRoomWithParams(@Valid RoomSearch roomSearch, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "reservation";
        }

        /** /rooms/search POST **/
        var availableRooms = roomService.availableRooms(roomSearch);
        model.addAttribute("rooms", availableRooms);
        model.addAttribute("search",roomSearch);

        System.out.println("available rooms size = " + availableRooms.size());
        return "reservation";
    }


    @GetMapping("/reservation-confirmation")
    public String bookRoomWithDetails(@RequestParam("startDate") LocalDate startDate,
                                      @RequestParam("endDate") LocalDate endDate,
                                      Principal principal,
                                      Model model, Reservation reservation){
        User user = Optional.ofNullable(principal)
                .map(Principal::getName)
                .map(userRepository::findByEmail)
                .orElse(null);

        model.addAttribute("currentUser",user);
        model.addAttribute("isUserLoggedIn", Objects.nonNull(user));
        model.addAttribute("checkInDate",startDate);
        model.addAttribute("checkOutDate",endDate);
        model.addAttribute("reservation", reservation);

        return "reservation-confirmation";
    }

    @PostMapping ("/reservation-confirmation")
    public String saveNewReservation(@Valid Reservation reservation, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "reservation";
        }
        model.addAttribute("reservation", reservation);

        reservationService.saveReservation(reservation);
        return "reservation-confirmation";
    }

}
