package com.group3.lovelacehotel.controller;

import com.group3.lovelacehotel.model.Room;
import com.group3.lovelacehotel.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/rooms")
public class RoomController {

    private final RoomService roomService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("rooms", roomService.getAll());
        return "index";
    }


    @GetMapping(value = "/room-add")
    public String RoomRegistration(Model map, Room room) {
        map.addAttribute("pageName", "Add New Room!");
        return "room-add";
    }


    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") Long id, Model model) {
        roomService.delete(id);
        return index(model);
    }

    @GetMapping("/edit/{id}")
    public String editById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("pageName", "Edit New Room");

        Room room = roomService.getById(id);
        model.addAttribute("room", room);

        return "room-edit";
    }


    @PostMapping("/update/{id}")
    public String updateRoom(@PathVariable("id") Long id, @Valid Room room, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "room-edit";
        }

        roomService.updateRoom(id, room);

        return index(model);
    }
}
