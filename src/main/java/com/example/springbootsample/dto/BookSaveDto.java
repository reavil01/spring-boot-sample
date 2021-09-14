package com.example.springbootsample.dto;

import com.example.springbootsample.domain.Book;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
@Setter
public class BookSaveDto {
    private String name;
    private int price;
    private LocalDate releaseDate;

    public Book toEntity() {
        return new Book(name, price, releaseDate);
    }
}
