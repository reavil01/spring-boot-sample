package com.example.springbootsample.dto;

import com.example.springbootsample.entity.Book;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class BookCreateRequest {
    private final String name;
    private final int price;
    private final LocalDate releaseDate;

    public BookCreateRequest(
            @JsonProperty("name") final String name,
            @JsonProperty("price") final int price,
            @JsonProperty("releaseDate") final LocalDate releaseDate
    ) {
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
}
