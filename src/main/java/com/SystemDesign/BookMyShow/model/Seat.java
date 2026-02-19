package com.SystemDesign.BookMyShow.model;

import com.SystemDesign.BookMyShow.model.enums.SeatType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Seat extends BaseModel{
    private String name;

    @Enumerated(EnumType.ORDINAL)
    private SeatType seatType;

    @ManyToOne
    private Screen screen;
}

/*
    seat : screen --> M : 1
    1       1
    M       1
 */
