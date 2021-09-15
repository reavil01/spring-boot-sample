package com.example.springbootsample.entity;


import com.example.springbootsample.dto.BookUpdateDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Book {
    public Book(String name, int price, LocalDate releaseDate) {
        this.name = name;
        this.price = price;
        this.releaseDate = releaseDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long id = 0L;

    @Column
    @Setter
    private String name;

    @Column
    @Setter
    private int price;

    @Column
    @Setter
    private LocalDate releaseDate;

    public void update(BookUpdateDto request) {
        this.name = request.getName();
        this.price = request.getPrice();
        this.releaseDate = request.getReleaseDate();
    }
}
