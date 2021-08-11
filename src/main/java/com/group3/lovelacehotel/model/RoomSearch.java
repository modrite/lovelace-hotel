package com.group3.lovelacehotel.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class RoomSearch {
    @NotNull
    private Long numberOfAdults;
    @NotNull
    private Long numberOfChildren;
    @NotNull
    private LocalDateTime checkInDate;
    @NotNull
    private LocalDateTime checkOutDate;
}
