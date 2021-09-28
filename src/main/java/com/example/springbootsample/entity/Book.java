package com.example.springbootsample.entity;


import com.example.springbootsample.dto.BookUpdateRequest;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int price;
    private LocalDate releaseDate;

    protected Book() {}

    public Book(long id, String name, int price, LocalDate releaseDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.releaseDate = releaseDate;
    }

    public Book(String name, int price, LocalDate releaseDate) {
        this(0L, name, price, releaseDate);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }
}
