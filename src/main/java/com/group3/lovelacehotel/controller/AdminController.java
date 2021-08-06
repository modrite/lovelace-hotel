package com.group3.lovelacehotel.controller;

import com.group3.lovelacehotel.model.Room;
import com.group3.lovelacehotel.service.AdminService;
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
@RequestMapping("/admins")
public class AdminController {

    private final AdminService adminService;





}
