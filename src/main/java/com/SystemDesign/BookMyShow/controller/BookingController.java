package com.SystemDesign.BookMyShow.controller;

import com.SystemDesign.BookMyShow.DTOs.BookingRequestDTO;
import com.SystemDesign.BookMyShow.DTOs.BookingResponseDTO;
import com.SystemDesign.BookMyShow.DTOs.enums.ResponseStatus;
import com.SystemDesign.BookMyShow.model.Booking;
import com.SystemDesign.BookMyShow.service.BookingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    private BookingService bookingService;

    /*
        Now, inside the bookingController, we are injecting the bookingService class object.
        This means that the controller is not responsible for creation of the service class
        object, it will just receive the object of servuce class.

        This follows:
        Dependency Inversion Principle(DIP)
        High-level modules should not depend on low-level modules.

        This promotes loose coupling, which shows its a good design.

        This constructor injection is completely equivalent to @Autowired.
     */
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/bookTicket")
    public BookingResponseDTO bookTicket(@RequestBody BookingRequestDTO bookingRequestDTO) {
        BookingResponseDTO bookingResponseDTO = new BookingResponseDTO();

        try{
            Booking bookingTicket = bookingService.bookTicket(bookingRequestDTO);
            bookingResponseDTO.setBookingTicket(bookingTicket);
            bookingResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);
            return bookingResponseDTO;
        } catch(Exception e) {
            bookingResponseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }

        return null;
    }
}
