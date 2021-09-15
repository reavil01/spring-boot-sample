package com.example.springbootsample.dto;

import com.example.springbootsample.entity.Book;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class BookResponseDto {
    private final long id;
    private String name;
    private int price;
    private LocalDate releaseDate;

    public BookResponseDto(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.price = book.getPrice();
        this.releaseDate = book.getReleaseDate();
    }
}
