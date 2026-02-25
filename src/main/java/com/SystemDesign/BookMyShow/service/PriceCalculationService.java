package com.SystemDesign.BookMyShow.service;

import com.SystemDesign.BookMyShow.model.ShowSeat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculationService {
    public Long calculatePrice(List<ShowSeat> showSeats) {
        Long amount = 0L;
        for(ShowSeat showSeat: showSeats) {
            amount += showSeat.getPrice();
        }
        return amount;
    }
}
