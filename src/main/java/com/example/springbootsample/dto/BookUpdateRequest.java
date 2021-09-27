package com.example.springbootsample.dto;

import java.time.LocalDate;

public class BookUpdateRequest {
    private String name;
    private int price;
    private LocalDate releaseDate;

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public BookUpdateRequest(final String name, final int price, final LocalDate releaseDate) {
        this.name = name;
        this.price = price;
        this.releaseDate = releaseDate;
    }
}
