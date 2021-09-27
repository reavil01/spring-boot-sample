package com.example.springbootsample.entity;


import com.example.springbootsample.dto.BookUpdateDto;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long id = 0L;
    private String name;
    private int price;
    private LocalDate releaseDate;

    protected Book() {
    }

    public Book(String name, int price, LocalDate releaseDate) {
        this.name = name;
        this.price = price;
        this.releaseDate = releaseDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void update(BookUpdateDto request) {
        this.name = request.getName();
        this.price = request.getPrice();
        this.releaseDate = request.getReleaseDate();
    }
}
