package com.example.springbootsample.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class BookPatchRequest {
    private final String name;
    private final Integer price;
    private final LocalDate releaseDate;

    public BookPatchRequest(
            @JsonProperty(value = "name") final String name,
            @JsonProperty(value = "price") final Integer price,
            @JsonProperty(value = "releaseDate") final LocalDate releaseDate
    ) {
        this.name = name;
        this.price = price;
        this.releaseDate = releaseDate;
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
