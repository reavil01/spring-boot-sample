package com.example.springbootsample.dto;

import com.example.springbootsample.entity.Book;

import java.time.LocalDate;

public class BookResponse {
    private final long id;
    private final String name;
    private final int price;
    private final LocalDate releaseDate;

    public BookResponse(final Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.price = book.getPrice();
        this.releaseDate = book.getReleaseDate();
    }

    public long getId() {
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
