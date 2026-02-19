package com.SystemDesign.BookMyShow.model;

import com.SystemDesign.BookMyShow.model.enums.Feature;
import com.SystemDesign.BookMyShow.model.enums.ShowStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Enumeration;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "shows")
public class Show extends BaseModel {
    private Long startTime;
    private Long endTime;

    @ManyToOne
    private Movie movie;

    @ManyToOne
    private Screen screen;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> features;

    @Enumerated(EnumType.ORDINAL)
    private ShowStatus showStatus;
}

/*
    show : screen --> M : 1
    1       1
    M       1

    show : movie --> M : 1
    1       1
    M       1

 */