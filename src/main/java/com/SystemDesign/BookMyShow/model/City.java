package com.SystemDesign.BookMyShow.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
public class City extends BaseModel {
    private String name;

    @OneToMany
    private List<Theatre> theatres;
}

/*
    City : Theatre --> 1 : M
    1       M
    1       1

A {
    @Cardinality
    B b
}

 */
