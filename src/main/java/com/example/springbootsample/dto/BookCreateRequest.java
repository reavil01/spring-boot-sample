package com.example.springbootsample.dto;

import com.example.springbootsample.entity.Book;

import java.time.LocalDate;

public class BookCreateRequest {
    private String name;
    private int price;
    private LocalDate releaseDate;

    public BookCreateRequest(final String name, final int price, final LocalDate releaseDate) {
        this.name = name;
        this.price = price;
        this.releaseDate = releaseDate;
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

    public Book toEntity() {
        return new Book(name, price, releaseDate);
    }
}
