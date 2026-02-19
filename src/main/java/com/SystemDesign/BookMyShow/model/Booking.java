package com.SystemDesign.BookMyShow.model;

import com.SystemDesign.BookMyShow.model.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Booking extends BaseModel {
    private Long number;

    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;

    @ManyToOne
    private User user;

    @ManyToOne
    private Show show;

    @ManyToMany
    private List<ShowSeat> showSeats;

    @ManyToOne
    private Screen screen;

    @ManyToOne
    private Theatre theatre;

    @ManyToOne
    private City city;

    private Payment payment;
}

/*
    booking : user --> M : 1
    1           1
    M           1

    booking : show --> M : 1
    1           1
    M           1

    booking : showSeats --> M : M
    1           M
    M           1

    Why cardinality between booking and showSeats = M : M ?
    - Booking : showSeats
      booking_id = 1, seat_id = 1, show_id = 45
                      seat_id = 2, show_id = 45
                      seat_id = 3, show_id = 45 --> cancelled

      booking_id = 2, seat_id = 3, show_id = 45
                      seat_id = 4, show_id = 45

      thats why the for a particular booking_id, there are multiple showSeats possible.
      And the vice versa, you can proof yourself.


    booking : screen --> M : 1
    1           1
    M           1

    booking : theatre --> M : 1
    1           1
    M           1

    booking : city --> M : 1
    1           1
    M           1

    booking : payment --> 1 : 1
    1           1
    1           1

    Lets make an analysis for the booking : payment cardinality
    1st case scenario -
    Best common real world scenario -
    1 : 1
    Each booking has exactly one payment & each payment belongs to exactly one booking.
    No partial payments
    No retry of payments.

    2nd case scenraio -
    booking : payment --> 1 : M
    - For a single booking, the user is retrying multiple times for the payment of the
    booking.
    - Partial payments are being initialized
        total amount = 1000/-
        amount initiated by UPI - 800/-
        amount initiated by Credit card - 200/-

    3rd case scenario -
    booking : payment = M : 1
    - This case generally happens when a company pays for multiple bookings - basically
    bulk booking.


    => IMPORTANT: In our case, we will consider the basic case which is more likely
    to happen in a real-world scenario - 1 : 1

 */
