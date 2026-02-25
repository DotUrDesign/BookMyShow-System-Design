package com.SystemDesign.BookMyShow.service;

import com.SystemDesign.BookMyShow.DTOs.BookingRequestDTO;
import com.SystemDesign.BookMyShow.exceptions.*;
import com.SystemDesign.BookMyShow.model.*;
import com.SystemDesign.BookMyShow.model.enums.BookingStatus;
import com.SystemDesign.BookMyShow.model.enums.ShowSeatStatus;
import com.SystemDesign.BookMyShow.repository.ShowRepository;
import com.SystemDesign.BookMyShow.repository.ShowSeatRepository;
import com.SystemDesign.BookMyShow.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private UserRepository userRepository;
    private ShowSeatRepository showSeatRepository;
    private ShowRepository showRepository;
    private PriceCalculationService priceCalculationService;

    public BookingService(UserRepository userRepository,
                          ShowSeatRepository showSeatRepository,
                          ShowRepository showRepository,
                          PriceCalculationService priceCalculationService) {
        this.userRepository = userRepository;
        this.showSeatRepository = showSeatRepository;
        this.showRepository = showRepository;
        this.priceCalculationService = priceCalculationService;
    }

    public Booking bookTicket(BookingRequestDTO bookingRequestDTO) {
        Booking booking = new Booking();

        // extracting the details from the request dto
        Long userId = bookingRequestDTO.getUserId();
        List<Long> showSeatIds = bookingRequestDTO.getShowSeatIds();
        Long showId = bookingRequestDTO.getShowId();

        // no
        booking.setNo("TICKET_" + userId + "Show_" + showId);

        // user
        Optional<User> optionalUser = userRepository.getUserById(userId);

        if(optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found.");
        }
        booking.setUser(optionalUser.get());

        // show
        Optional<Show> optionalShow = showRepository.getShowById(showId);

        if(optionalShow.isEmpty()) {
            throw new ShowNotFoundException("Show is invalid");
        }
        booking.setShow(optionalShow.get());

        // screen
        Optional<Screen> optionalScreen = showRepository.getScreenByShowId(showId);

        if(optionalScreen.isEmpty()) {
            throw new ScreenNotFoundException("Screen not found. Invalid screen.");
        }
        booking.setScreen(optionalScreen.get());

        // showSeats
        List<ShowSeat> showSeats = showSeatRepository.findAllShowSeatsAndLock(showSeatIds);

        if(showSeats.size() != showSeatIds.size()) {
            throw new ShowSeatNotFoundException("All showSeats are not available.");
        }

        for(ShowSeat showSeat : showSeats) {
            if(!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)) {
                throw new SeatsNoLongerAvailableException("Some seats are no longer available");
            }
        }

        // all the seats we have checked, now we can proceed with the next steps.

        // payment is pending
        // bookingstatus = pending as payment is not done.
        booking.setBookingStatus(BookingStatus.PENDING);

        // give 10 mins to the user for the payment process.
        // within these 10 mins, we will lock the seats for other customers
        for(ShowSeat showSeat: showSeats) {
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
        }
        showSeatRepository.saveAll(showSeats);
        booking.setShowSeats(showSeats);

        // set the amount
        booking.setAmount(priceCalculationService.calculatePrice(showSeats));

        // now there 2 possibilities => either the payment will be confirmed
        // or the payment will be cancelled.
        // if the payment is confirmed, then book the seats and
        // change the showSeatStatus for those seats.
        for(ShowSeat showSeat: showSeats) {
            showSeat.setShowSeatStatus(ShowSeatStatus.BOOKED);
        }
        booking.setBookingStatus(BookingStatus.CONFIRMED);

        showSeatRepository.saveAll(showSeats);

        // else the payment is cancelled.
        // then rollback the whole process - rollback all the things which we have done above.

        return booking;
    }
}
