package com.group3.lovelacehotel.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class RoomSearch {
    @NotNull
    private Long numberOfAdults;
    @NotNull
    private Long numberOfChildren;
    @NotNull(message = "Please enter checkin date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate checkInDate;
    @NotNull(message = "Please enter checkout date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate checkOutDate;
}
