package com.example.springbootsample.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class BookUpdateDto {
    private String name;
    private int price;
    private LocalDate releaseDate;
}
