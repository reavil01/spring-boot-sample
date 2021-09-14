package com.example.springbootsample.domain;


import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
public class Book {
    protected Book() {}
    public Book(String name, int price, LocalDate releaseDate) {
        this.name = name;
        this.price = price;
        this.releaseDate = releaseDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = 0L;

    @Column
    private String name;

    @Column
    private int price;

    @Column
    private LocalDate releaseDate;
}
