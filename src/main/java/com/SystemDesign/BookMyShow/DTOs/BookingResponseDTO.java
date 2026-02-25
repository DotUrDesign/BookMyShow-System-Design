package com.SystemDesign.BookMyShow.DTOs;

import com.SystemDesign.BookMyShow.DTOs.enums.ResponseStatus;
import com.SystemDesign.BookMyShow.model.Booking;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponseDTO {
    private Booking bookingTicket;
    private ResponseStatus responseStatus;
}
