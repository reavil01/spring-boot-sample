package com.example.springbootsample.dto;

import com.example.springbootsample.entity.Book;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class BookSaveDto {
    private String name;
    private int price;
    private LocalDate releaseDate;

    public Book toEntity() {
        return new Book(name, price, releaseDate);
    }
}
