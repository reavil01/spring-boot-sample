package com.example.springbootsample.dto;

import com.example.springbootsample.spring.entity.BookEntity;

import java.time.LocalDate;

public class BookResponse {
    private final long id;
    private final String name;
    private final int price;
    private final LocalDate releaseDate;

    public BookResponse(final BookEntity bookEntity) {
        this.id = bookEntity.getId();
        this.name = bookEntity.getName();
        this.price = bookEntity.getPrice();
        this.releaseDate = bookEntity.getReleaseDate();
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
