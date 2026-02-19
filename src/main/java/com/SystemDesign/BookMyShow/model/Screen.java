package com.SystemDesign.BookMyShow.model;

import com.SystemDesign.BookMyShow.model.enums.Feature;
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
public class Screen {
    private String name;

    @OneToMany
    private List<Seat> seats;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> features;
}

/*
    Screen seat --> 1 : M
    1       M
    1       1
 */
