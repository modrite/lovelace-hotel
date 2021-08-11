package com.group3.lovelacehotel.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class RoomSearch {
    @NotNull
    private Long numberOfAdults;
    @NotNull
    private Long numberOfChildren;
    @NotNull
    private LocalDate checkInDate;
    @NotNull
    private LocalDate checkOutDate;
}
