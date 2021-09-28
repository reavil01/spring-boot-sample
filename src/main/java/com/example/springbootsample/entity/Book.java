package com.example.springbootsample.entity;

import java.time.LocalDate;

import static java.util.Objects.requireNonNull;

public class Book {
    private final Long id;
    private final String name;
    private final Integer price;
    private final LocalDate releaseDate;

    public Book(
            final Long id,
            final String name,
            final Integer price,
            final LocalDate releaseDate) {
        this.id = requireNonNull(id);
        this.name = requireNonNull(name);
        this.price = requireNonNull(price);
        this.releaseDate = requireNonNull(releaseDate);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }
}
