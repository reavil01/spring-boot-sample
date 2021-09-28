package com.example.springbootsample.spring.entity;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int price;
    private LocalDate releaseDate;

    protected BookEntity() {
    }

    public BookEntity(
            final long id,
            final String name,
            final int price,
            final LocalDate releaseDate
    ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.releaseDate = releaseDate;
    }

    public BookEntity(
            final String name,
            final int price,
            final LocalDate releaseDate
    ) {
        this(0L, name, price, releaseDate);
    }

    public Long getId() {
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
