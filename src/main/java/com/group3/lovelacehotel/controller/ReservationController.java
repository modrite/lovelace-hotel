package com.group3.lovelacehotel.controller;

import com.group3.lovelacehotel.model.Reservation;
import com.group3.lovelacehotel.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping
    public String landingReservationPage() {
        return "redirect:/reservations/allReservations";
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

        reservationService.confirm(reservation);
        return "reservation";
    }


    @GetMapping(value = "/allReservations")
    public String allReservations(Model model, Reservation reservation) {
        model.addAttribute("pageName", "AllReservations!");
        model.addAttribute("reservations", reservationService.getAll());
        return "allReservations";
    }


    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") Long id, Model model) {
        reservationService.delete(id);
        return "redirect:/reservations/allReservations";
    }

    @GetMapping("/edit/{id}")
    public String editById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("pageName", "Edit New Reservation");

        Reservation reservation = reservationService.getById(id);
        System.out.println("reservation = " + reservation);

        model.addAttribute("reservation", reservation);

        return "edit-reservation";
    }


    @PostMapping("/update/{id}")
    public String updateReservation(@PathVariable("id") Long id, @Valid Reservation reservation, BindingResult result, Model model) {
        if (result.hasErrors()) {
            //this just describes errors
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors ) {
                System.out.println (error.getObjectName() + " - " + error.getDefaultMessage());
            }
            System.out.println("errorcheck reservation = " + reservation);
            System.out.println("errorcheck model = " + model);
            return "edit-reservation";
        }

        reservationService.updateReservation(id, reservation);

        return "redirect:/reservations/allReservations";
    }
}
