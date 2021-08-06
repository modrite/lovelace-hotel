package com.group3.lovelacehotel.controller;


import com.group3.lovelacehotel.model.Room;
import com.group3.lovelacehotel.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("rooms", roomService.getAll());
        return "rooms";
    }

    @GetMapping(value = "/add-room")

    public String addRoom(Model map, Room room) {

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

}
