package com.SystemDesign.BookMyShow.model;

import com.SystemDesign.BookMyShow.model.enums.ShowSeatStatus;
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
public class ShowSeat extends BaseModel {
    @ManyToOne
    private Show show;

    @ManyToOne
    private Seat seat;

    private int price;

    @Enumerated(EnumType.ORDINAL)
    private ShowSeatStatus showSeatStatus;

}

/*
    showseat : show --> M : 1
    1           1
    M           1

    showseat : seat --> M : 1
    1           1
    M           1
 */
