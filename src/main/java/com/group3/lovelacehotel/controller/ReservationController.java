package com.group3.lovelacehotel.controller;

import com.group3.lovelacehotel.model.Reservation;
import com.group3.lovelacehotel.service.ReservationService;
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
@RequestMapping(value = "/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping
    public String landingReservationPage() {
        return "redirect:reservations/allreservations";
    }

    @GetMapping("/new-reservation")
    public String newReservation(Reservation reservation) {
        return "reservation";
    }

    @PostMapping
    public String saveNewReservation(@Valid Reservation reservation, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "reservation";
        }

        reservationService.saveReservation(reservation);
        return "reservation";
    }


    @GetMapping(value = "/allreservations")
    public String allReservations(Model map, Reservation reservation) {
        map.addAttribute("pageName", "All Reservations!");
        return "allReservations";
    }


    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") Long id, Model model) {
        reservationService.delete(id);
        return "redirect:reservations/allreservations";
    }

    @GetMapping("/edit/{id}")
    public String editById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("pageName", "Edit New Reservation");

        Reservation reservation = reservationService.getById(id);
        model.addAttribute("reservation", reservation);

        return "reservation-edit";
    }

//
//    @PostMapping("/update/{id}")
//    public String updateReservation(@PathVariable("id") Long id, @Valid Reservation reservation, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "reservation-edit";
//        }
//
//        reservationService.updateReservation(id, reservation);
//
//        return index(model);
//    }
}
